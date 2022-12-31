import {TestBed} from '@angular/core/testing';

import {FileClientService} from './file-client.service';
import {HttpClient} from "@angular/common/http";
import {BpmnProcess} from "../types/bpmn.types";
import {of} from "rxjs";
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('FileClientService', () => {

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    }).compileComponents();
  });

  it('should be created', () => {
    const service: FileClientService = TestBed.get(FileClientService);
    expect(service).toBeTruthy();
  });
});

describe('FileClientService', () => {
  let fileClientService: FileClientService;
  let httpClientSpy: jasmine.SpyObj<HttpClient>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    }).compileComponents();
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['post']);
    fileClientService = new FileClientService(httpClientSpy);
  });

  it('should return expected process (HttpClient called once)', (done: DoneFn) => {
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
});
