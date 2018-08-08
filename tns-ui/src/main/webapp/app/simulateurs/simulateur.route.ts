import { lmnpRoute } from './lmnp/lmnp.route';
import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../shared';

const SIMULATEUR_ROUTES = [
lmnpRoute
];

export const simulateurState: Routes = [{
    path: '',
    data: {
        authorities: ['ROLE_ADMIN']
    },
    canActivate: [UserRouteAccessService],
    children: SIMULATEUR_ROUTES
}
];
