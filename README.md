# Vaadin + Spring Boot Example

This example app shows you how to build a Vaadin Fusion and Spring Boot app and add Okta for authentication. Please read [A Quick Guide to Security with Vaadin Fusion and Spring Boot](https://developer.okta.com/blog/2020/11/09/vaadin-spring-boot) to see how it was created.

**Prerequisites:** [Java 11](https://adoptopenjdk.net/)+ and Maven 3.6+.

> [Okta](https://developer.okta.com/) has Authentication and User Management APIs that reduce development time with instant-on, scalable user infrastructure. Okta's intuitive API and expert support make it easy for developers to authenticate, manage and secure users and roles in any application.

* [Getting Started](#getting-started)
* [Help](#help)
* [Links](#links)
* [License](#license)

## Getting Started

If you don't have one, [create an Okta Developer account](https://developer.okta.com/signup/). After you've completed the setup process, log in to your account and navigate to **Applications** > **Add Application**. Click **SPA** and **Next**. On the next page, enter the following values and click **Done**.

- **Name**: Vaadin Fusion
- **Base URIs**: `http://localhost:8080`
- **Login redirect URIs**: `http://localhost:8080/callback`
- **Logout redirect URIs**: `http://localhost:8080`
- **Grant type allowed**: Authorization Code

Copy your issuer into `src/main/application.properties`:

```properties
okta.oauth2.issuer=https://{yourOktaDomain}/oauth2/default
```

Copy your issuer and client ID into `frontend/auth.ts`:

```ts
const authClient = new OktaAuth({
  issuer: 'https://{yourOktaDomain}/oauth2/default',
  clientId: '{yourClientID}',
  redirectUri: 'http://localhost:8080/callback',
  pkce: true,
});
```

Start the app by running Maven:

```
mvn
```

## How it works

### Back end

**`pom.xml`** Vaadin Spring Boot starter with the following added dependencies:

- okta-spring-boot-starter
- lombok

**`SecurityConfiguration.java`** Enables Spring Security and lets Vaadin endpoints handle the authorization.

**`PeopleEndpoint.java`** is a Vaadin endpoint that exposes a REST endpoint and generates TS interfaces for accessing it in a type-safe manner.
**Note:** Vaadin endpoints require authentication by default unless you opt-out by adding a `@AnonymousAllowed` annotation to the class or metod.

### Front end

**`auth.ts`** contains the [Okta Auth JS](https://github.com/okta/okta-auth-js) configuration. It exposes an API for:

- checking if a user is authenticated
- signing in/out
- handling login redirects
- providing an access token for HTTP requests

**`index.ts`** defines the application routes and authentication handling.

- an `authGuard` action is used to check if the user is authenticated. If not, the path is saved and the user is redirected to `/login`
- the `/callback` route has an action that parses the response from the auth server and either redirects to the initially requested route or back to login.

**`connect-client.ts`** defines a middleware that adds the access token to all server requests.

**`login-view.ts`** has a login form and calls the `signIn` API that `auth.ts` exposes.

## Links

This example uses the following libraries:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Vaadin 17](https://vaadin.com/)
- TypeScript-based LitElement views
- [Okta Auth JS SDK](https://github.com/okta/okta-auth-js#readme)
- [Okta Spring Boot Starter](https://github.com/okta/okta-spring-boot#readme)

## Help

Please post any questions as comments on the [blog post](https://developer.okta.com/blog/2020/11/09/vaadin-spring-boot) or visit our [Okta Developer Forums](https://devforum.okta.com/).

## License

Apache 2.0, see [LICENSE](LICENSE).

