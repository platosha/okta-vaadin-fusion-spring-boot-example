import { html } from 'lit';
import { customElement, state } from 'lit/decorators';
import { View } from '../view';
import { signIn } from '../../auth';
import '@vaadin/vaadin-login/vaadin-login-form';

@customElement('login-view')
export class LoginView extends View {
  @state()
  private error = !!new URLSearchParams().get('error');

  render() {
    return html`
     <style>
       login-view {
         display: flex;
         flex-direction: column;
         height: 100%;
         align-items: center;
         justify-content: center;
       }
     </style>
     <vaadin-login-form 
       @login=${this.login} 
       ?error=${this.error}
       no-forgot-password
     ></vaadin-login-form>
   `;
  }

  async login(e: CustomEvent) {
    try {
      await signIn(e.detail.username, e.detail.password);
    } catch (e) {
      this.error = true;
    }
  }
}
