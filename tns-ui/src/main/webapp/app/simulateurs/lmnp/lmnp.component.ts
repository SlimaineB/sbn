import { Component, OnInit } from '@angular/core';
import { LmpnIn } from '../../shared';
import { LmnpService } from './lmnp.service';

@Component({
    selector: 'jhi-lmnp',
    templateUrl: './lmnp.component.html'
})
export class JhiLmnpComponent implements OnInit {

    lmnpIn: LmpnIn;

    constructor(
        private lmnpService: LmnpService
    ) {
    }

    ngOnInit() {
        this.lmnpIn = {};
        this.lmnpIn.loyerNet = 200;
    }

    simulate() {
        console.log('Simulatin launched : ' + this.lmnpIn.prixAchat);
        this.lmnpService.simulateLmnp(this.lmnpIn).subscribe((res) => {

            console.log(res.body);
         //   this.links = this.parseLinks.parse(res.headers.get('link'));
          //  this.totalItems = + res.headers.get('X-Total-Count');
        });
    }
}
