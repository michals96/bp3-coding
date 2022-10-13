import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {JsonRenderComponent} from './json-render.component';

describe('JsonRenderComponent', () => {
  let component: JsonRenderComponent;
  let fixture: ComponentFixture<JsonRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [JsonRenderComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JsonRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
