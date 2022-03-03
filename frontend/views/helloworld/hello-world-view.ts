import { Notification } from '@vaadin/notification';
import { View } from '../view';
import '@vaadin/vaadin-button';
import '@vaadin/vaadin-text-field';
import { css, html } from 'lit';
import { customElement } from 'lit/decorators';

@customElement('hello-world-view')
export class HelloWorldView extends View {
  name: string = '';

  static get styles() {
    return css`
      :host {
        display: block;
        padding: 1em;
      }
    `;
  }
  render() {
    return html`
      <vaadin-text-field label="Your name" @value-changed="${this.nameChanged}"></vaadin-text-field>
      <vaadin-button @click="${this.sayHello}">Say hello</vaadin-button>
    `;
  }
  nameChanged(e: CustomEvent) {
    this.name = e.detail.value;
  }

  sayHello() {
    Notification.show(html`
      Hello ${this.name}
    `);
  }
}
