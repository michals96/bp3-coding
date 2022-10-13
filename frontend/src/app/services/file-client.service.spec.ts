import {TestBed} from '@angular/core/testing';

import {FileClientService} from './file-client.service';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {BpmnProcess} from "../types/bpmn.types";
import {of} from "rxjs";
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('FileClientService creation tests', () => {

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    }).compileComponents();
  });

  it('File service should be created', () => {
    const service: FileClientService = TestBed.get(FileClientService);
    expect(service).toBeTruthy();
  });
});

describe('FileClientService HTTP tests', () => {
  let fileClientService: FileClientService;
  let httpClientSpy: jasmine.SpyObj<HttpClient>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    }).compileComponents();
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['post']);
    fileClientService = new FileClientService(httpClientSpy);
  });

  it('Should return expected process (HttpClient called once)', (done: DoneFn) => {
    const expectedProcess: BpmnProcess =
      {
        "nodes": [
          {
            "id": 0,
            "name": "Start",
            "type": "Start"
          },
          {
            "id": 2,
            "name": "B",
            "type": "HumanTask"
          },
          {
            "id": 4,
            "name": "D",
            "type": "HumanTask"
          },
          {
            "id": 5,
            "name": "End",
            "type": "End"
          }
        ],
        "edges": [
          {
            "from": 0,
            "to": 2
          },
          {
            "from": 2,
            "to": 4
          },
          {
            "from": 4,
            "to": 5
          }
        ]
      };

    httpClientSpy.post.and.returnValue(of(expectedProcess));

    let blob = new Blob([""], {type: 'text/html'});
    blob["lastModifiedDate"] = "";
    blob["name"] = "filename";

    let fakeF = <File>blob;

    fileClientService.uploadFile(fakeF).subscribe({
      next: process => {
        expect(process)
          .withContext('expected process')
          .toEqual(expectedProcess);
        done();
      },
      error: done.fail
    });

    expect(httpClientSpy.post.calls.count())
      .withContext('one call')
      .toBe(1);
  });

  it('Should return an error when the server returns a 404', (done: DoneFn) => {
    const errorResponse = new HttpErrorResponse({
      error: 'test 404 error',
      status: 404, statusText: 'Not Found'
    });

    let blob = new Blob([""], {type: 'text/html'});
    blob["lastModifiedDate"] = "";
    blob["name"] = "filename";

    let fakeF = <File>blob;

    httpClientSpy.post.and.returnValue(of(errorResponse));

    fileClientService.uploadFile(fakeF).subscribe({
      next: process => {
        done()
      },
      error: error => {
        expect(error.message).toContain('test 404 error');
        done();
      }
    });
  });
});
