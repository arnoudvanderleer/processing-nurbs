# The Nurbs library

## Introduction
Welcome to the source code of the Processing Nurbs library! This library allows one to draw Non-Uniform Rational B-Splines (abbreviated as nurbs) and nurbs surfaces.
It allows for drawing these with an arbitrary precision, and them in a point. See [the webpage](https://arnoudvanderleer.github.io/processing-nurbs/) for more info on the library and how to add it to your processing environment.

## Repository layout
The main source code is in the `/src` directory. The `/test` directory contains the test source code. The `/examples` directory contains the examples.
The `/web` directory contains the source for the webpage. The `/resources` directory contains the files that are needed for building the project.
Compiling the project recreates the `/docs` directory, from which the [github.io webpage](https://arnoudvanderleer.github.io/processing-nurbs/) is served as well.

## Building
To build a distribution, go into the `/build` folder and run `ant` (which will run as `ant clean` by default). This will
- Compile the library to the `/docs/download` folder;
- Compile the webpage from the `/web` folder to the `/docs` folder;
- Generate documentation using `javadoc` to `/docs/reference`;
