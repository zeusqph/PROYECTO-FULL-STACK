import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateLogin } from './create-login';

describe('CreateLogin', () => {
  let component: CreateLogin;
  let fixture: ComponentFixture<CreateLogin>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateLogin]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateLogin);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
