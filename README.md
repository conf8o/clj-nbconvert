## clj-nbconvert

Converts jupyter files to html format documents with "jupyter nbconvert --to" command.

## Usage(currently)

1. Set following properties in `src/clj_nbconvert/config.clj`.
    * :input-path - Jupyter files in its lower hierarchy will be converted to html documents.

2. Do main function.

```
lein run
```

3. Files after conversion are in the same hierarchy as files before conversion.(output-path is planned)

## Planned

* Other format.
* Option of output-path.
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
