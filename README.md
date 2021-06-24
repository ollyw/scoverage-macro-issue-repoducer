# Reproducer for Scoverage issue

## Summary

Scoverage fails to provide coverage for objects that use macro annotations. This example is based upon real life usage with the ZIO accessible macro with the ZIO 1.x module pattern. Methods that are added by the developer do not have code coverage created for them although they should as only macro generated code should be ignored without the object body.

This project contains two objects representing services, that are equivalent, except that one has the manually generated code and the other uses the accessible macro

## The cause

Within the plugin.scala code there is some code that ignores macro expanded code, _regardless_ of whether the code has original line positions available for parts of the macro expanded code:
```
def process(tree: Tree): Tree = {
    tree match {

        ...
        
        // ignore macro expanded code, do not send to super as we don't want any children to be instrumented
        case t
            if t.attachments.all
              .toString()
              .contains("MacroExpansionAttachment") =>
          t
```

Ideally this can recursively inspect the macro expanded code and instrument the parts of the tree that have `pos` different from the macro `pos`. I modified the code as an experiment, and it worked to a degree, but the output reports don't work correctly. A proper fix is required

## To test

Run:

`sbt clean coverage test coverageReport`

Then look at the coverage report in `target/scala-2.13/scoverage-report/index.html`

