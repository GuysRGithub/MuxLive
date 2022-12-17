import { NgModule } from '@angular/core';
import { AuthModule } from 'angular-auth-oidc-client';


@NgModule({
    imports: [AuthModule.forRoot({
        config: {
            authority: 'https://dev-r3vbps5kxta1ef0w.us.auth0.com',
            redirectUrl: window.location.origin,
            clientId: 'cTfHhIFiw902U8UeveabHpk83zWrQUuW',
            scope: 'openid profile offline_access',
            responseType: 'code',
            silentRenew: true,
            useRefreshToken: true,
        }
      })],
    exports: [AuthModule],
})
export class AuthConfigModule {}
