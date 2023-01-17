# Minecraft-Forge-Porting-Tool

Tool who help for porting task between two versions of minecraft forge

For this, the tool is divided into three parts:

- Automated detection of source code changes
- Transformation of the old source code
- Documentation and logs

## Preambule

This tool is built around a file: the settings for content transformations (transformations.settings

This file
- cites all known source code changes made between the two versions of Minecraft. A class renamed, moved, a method modified, an element replaced.
- will be enhanced by what the user finds, or by an autodetection system if the user wishes to use it. A graphical interface will allow this file to be edited in a more readable way.

## Automated detection of source code changes

To automatically detect changes in the source code of Minecraft, an analyzer will compare the old source code (original) and a new one (which must be provided by the user)

It will determine
1) which classes, packages, packages info and module-info have disappeared and which have been added
2) the classes that were only moved to another package (if they have the exact same name), renamed and/or moved that has the exact same content, in the case where the class does not have the same content, has been moved and renamed, it will determine an equivalence rate by comparing all the classes that have disappeared with the new ones according to their content.
3) To improve the match rate more accurately. The parser will use the transform settings file (which cites known source code changes between the two versions of minecraft), to modify the new source code, rolling back the changes. (in fact, the analyzer will do the opposite of what the tool allows, in order to bring the new source code closer to the old one, so this intermediate code will look a lot like the old one and therefore the rate will be more accurate).
4) The analyzer will determine the modifications of each of the classes.
5) This information may be used automatically to convert the old source code, or in a supervised manner by a user who may agree to use such or such data and refuse others.
6) Logs and documentation can be written (at the user's choice)

## Transformation of the old source code

- The old mod source code will be modified according to the transformations.settings file.

## Documentation and logs

- Thanks to the settings file and the auto-detection system, documentation in markdown can be generated (and later in html/css, xml, json, and see other languages)

It can be declined in several human languages, through language files.

- The tool will provide logs (if the user accepts it) by several levels (errors, warnings, information, autodetection system etc)
