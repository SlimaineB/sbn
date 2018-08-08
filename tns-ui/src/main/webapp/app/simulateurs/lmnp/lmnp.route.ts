import { Route } from '@angular/router';

import {  JhiLmnpComponent } from './lmnp.component';

export const lmnpRoute: Route = {
    path: 'jhi-lmnp',
    component: JhiLmnpComponent,
    data: {
        pageTitle: 'global.menu.admin.apidocs'
    }
};
