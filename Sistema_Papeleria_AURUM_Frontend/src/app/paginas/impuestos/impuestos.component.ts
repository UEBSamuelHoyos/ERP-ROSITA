import { Component, OnInit } from '@angular/core';
import { ImpuestosService, Impuesto } from 'src/app/service/impuestos.service';

@Component({
  selector: 'app-impuestos',
  templateUrl: './impuestos.component.html',
  styleUrls: ['./impuestos.component.scss']
})
export class ImpuestosComponent implements OnInit {

  impuestos: Impuesto[] = [];

  constructor(private impuestosService: ImpuestosService) {}

  ngOnInit(): void {
    this.listarImpuestos();
  }

  listarImpuestos() {
    this.impuestosService.getImpuestos().subscribe(data => {
      this.impuestos = data;
    });
  }
}
