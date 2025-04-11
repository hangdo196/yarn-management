# Projekt Yarn App

### SPRINGBOOT
framework for java simplifies the development of stand-alone, production-ready Spring applications. It eliminates the need for complex configurations and allows developers to focus on writing business logic.

### H2 Database
H2 is a lightweight, fast, open-source relational database written in Java. It can run in embedded mode (in-memory) or server mode. H2 is often used for development, testing, and small-scale applications where a full-fledged database like MySQL or PostgreSQL is unnecessary. http://localhost:8080/h2-console

### REST API
A REST API (Representational State Transfer Application Programming Interface) is a way for applications to communicate with each other over the internet using HTTP requests. It allows clients (like web apps, mobile apps, or other services) to interact with a server by sending and receiving data in formats like JSON or XML.

### Plan 
- Frontend with Angular
- Containerize the application with Docker

### Angular

##### Module 
Module is a container for organizing your application’s components, services, directives, and pipes. 
After Angular 14 we can use standalone component to simplify the app structure. We can create more modular, reusable, and self-contained components without needing to declare them in an NgModule. In standalone components, we specify the necessary imports directly in the component

##### Service
Services in Angular are used to handle logic and functionality that is reusable across components, such as data retrieval from APIs, authentication, etc. Services are injected into components and other services using dependency injection.
- @Injectable(): This decorator marks the class as a service that can be injected into other components or services using Angular's dependency injection system.

##### Component
Components are the building blocks of Angular applications. Each component consists of: HTML Template, CSS Styles, TypeScript Class Components can be nested, and Angular uses a tree structure to handle component relationships.
- ngOnInit(): This lifecycle hook is called when the component is initialized. It's a good place to put logic that should run once the component is loaded.
- <a routerLink="/users">: This is an Angular Router directive used to navigate between different views or pages within the application. The routerLink binds to a route, and when clicked, it navigates to the path /users, which is associated with the UserListComponent.

##### Directive
Directives are special markers on elements (in templates) that attach behavior to them or manipulate the DOM.

##### Routing
Angular includes a Router that lets you define different routes in your application, mapping URL paths to specific components. This is used to create navigation between different views or pages in a single-page application.
- The <router-outlet></router-outlet> directive tells Angular where to insert the component that matches the current route. This is a placeholder where the routed components will be displayed based on the active route.
- const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'users', component: UserListComponent },
];
In this case, if the user visits the home route (/), the HomeComponent will be displayed, and if they visit /users, the UserListComponent will appear in the <router-outlet>.

##### Observables
Angular makes extensive use of RxJS (Reactive Extensions for JavaScript) to manage asynchronous operations. Observables are used to handle streams of data over time, like API responses, user input, etc.