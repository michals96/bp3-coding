# BP3 Angular Frontend
Thank you for participating in the BP3 Coding Exercise. This is a small Angular project that captures the essence of 
the type of work that we do here at BP3. The exercise should only take an hour or two to complete.

## Exercise Summary
Here at BP3 we work with BPM process diagrams a great deal. This exercise has to do with removing the non-human steps
from a process diagram and listing only the human steps along with the start and end nodes.

## Important Information
There are a couple of directories to note:
* **Requirements Document** - The detailed requirements for the project are found in the `docs` directory in the file 
named `2019 Coding Challenge.pdf`. If there are differences between this `README` and the `2019 Coding Challenge.pdf`
the `2019 Coding Challenge.pdf` should be considered correct.
* **Project Data** - The JSON data files to be used are found in the `data` directory.

## Prerequisites
This project was generated with [Angular CLI](https://cli.angular.io/) version 8.3.5.
* Ensure you have the [prerequisites for the Angular CLI](https://github.com/angular/angular-cli#prerequisites) 
installed and working

## Running the JSON Data Server
A small HTTP server is provided to produce the JSON data needed by your Angular application. You can start it by 
running `npm run data-server` which will start the server listening on port `3000`.

## Using the Angular CLI
* Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you 
change any of the source files.
* Run `ng generate component component-name` to generate a new component. You can also 
use `ng generate directive|pipe|service|class|guard|interface|enum|module`.
* Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. 
Use the `-prod` flag for a production build.
* Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).
* Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).