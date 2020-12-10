## clj-nbconvert

Converts jupyter files to html format documents with "jupyter nbconvert --to" command.

## Usage(currently)

1. Set following properties of config in `resources/config.clj`.
    * :input-path - Jupyter files in its lower hierarchy will be converted to specified format.
    * :output-path - Formatted files will be in this path, retaining hierarchy.

2. Do main function.

```
lein run
```

## Planned

* Other format.(Currently only html is supported)
* Standalone.

## License

Copyright © 2020 conf8o

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
