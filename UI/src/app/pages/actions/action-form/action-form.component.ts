import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-action-form',
  templateUrl: './action-form.component.html',
  styleUrls: ['./action-form.component.scss']
})
export class ActionFormComponent {
  @Input() public request: any;
  @Output() public submit = new EventEmitter<any>();
  @Output() public close = new EventEmitter<any>();

  constructor(private fb: FormBuilder) {}
  form!: FormGroup;


  save() {
    this.form.markAllAsTouched();
    if (this.form.valid) {
        var body = this.form.getRawValue();
        this.submit.emit(body);
    }
  }
  ngOnInit(): void {
    const { name, duration } =
      this.request;

      this.form = this.fb.group({
      name: this.fb.control(name, [Validators.required]),
      duration: this.fb.control(duration, [Validators.required, Validators.min(0)])
    });
  }
}
