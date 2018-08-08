import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TnsUiSharedModule } from '../shared';
/* jhipster-needle-add-admin-module-import - JHipster will add admin modules imports here */

import {
    simulateurState,
    JhiLmnpComponent
} from './';

@NgModule({
    imports: [
        TnsUiSharedModule,
        RouterModule.forChild(simulateurState),
        /* jhipster-needle-add-admin-module - JHipster will add admin modules here */
    ],
    declarations: [
        JhiLmnpComponent
    ],
    entryComponents: [
        JhiLmnpComponent
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TnsUiSimulateurModule {}
