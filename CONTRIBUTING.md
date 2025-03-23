# Contributing to Intersmash

Thank you for contributing to the [Intersmash Tests repository](https://github.com/Intersmash/intersmash-tests)!

When submitting changes, try to follow used patterns and prepare a clean PR that should ideally fix one issue. 
A new unit test _should_ to be part of the PR in case the changes are related to a common code base.

## Adding code

To contribute changes to Intersmash Tests please open a pull request against the `main` branch by referencing the 
issue that tracks the work. 

As said, the changes must be tracked by a [GitHub issue](https://github.com/Intersmash/intersmash-tests/issues), 
so feel free to open one to manage Intersmash Tests feature requests, requests for enhancements and bugs.

Once you're satisfied with your changes, push them by opening a PR referencing the GitHub issue, e.g.: 
_"\[issue 1\] - Create a test to validate WildFly + MicroProfile Reactive Messaging with Kafka scenario"_. 
Commit messages should include the issue tracker to, e.g.: _"\[issue-1\] - Update supported Kafka version"_

Once pushed, automatic CI checks will be run to test the changes, and reported on the GitHub pull request.

#### Creating an issue

To report an issue with this project, please open a new 
[GitHub issue](https://github.com/Intersmash/intersmash-tests/issues).
Choose the proper template and fill it with the required information.

### Code conventions

Automatic code formatting and imports sorting plugins are applied on the project. Run
```
mvn spotless:apply
```

to format your code before sending it for revision.
CI jobs will run the checks (`mvn spotless:check`) and fail in case of wrong formatting.
