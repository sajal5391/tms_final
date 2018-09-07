import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveCmsComponent } from './approve-cms.component';

describe('ApproveCmsComponent', () => {
  let component: ApproveCmsComponent;
  let fixture: ComponentFixture<ApproveCmsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApproveCmsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApproveCmsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
