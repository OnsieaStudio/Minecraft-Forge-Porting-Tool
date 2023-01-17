# TODO

## SETUP

| Name                                  | do ?                  |
| -------                               | ------------------    |
| (Repository)                          | (:white_check_mark:)  |
| LICENSE.md                            | :white_check_mark:    |
| LICENSE_NOTICE.md                     | :white_check_mark:    |
| README.md                             | :x:                   |
| CONTRIBUTING.md                       | :white_check_mark:    |
| CONTRIBUTORS.md                       | :white_check_mark:    |
| CODE_OF_CONDUCT.md                    | :white_check_mark:    |
| CONVENTIONS.md                        | :white_check_mark:    |
| TODO.md                               | :white_check_mark:    |
| SECURITY.md                           | :x:                   |
| Base sources, resources and folders   | :x:                   |
| CodeQL setup                          | :x:                   |
| pom.xml                               | :x:                   |
| Maven setup                           | :x:                   |
| Maven repository                      | :x:                   |
| Gradle setup                          | :x:                   |
| Library jar files available in branch | :x:                   |
| Getting Started example               | :x:                   |

## Wiki

| Name                                  | do ?                  |
| -------                               | ------------------    |
| Initial Wiki pages                    | :x:                   |
| Wiki contact page                     | :x:                   |
| Wiki license page                     | :x:                   |
| Todo page                             | :x:                   |


## Tool

### General

| Name                                  		                      | do ?                  |
| -------                               		                      | ------------------    |
| Auto detection of changes in Minecraft source code   		        | :x:                   |
| Automatic writing of documentation in markdown            	    | :x:                   |
| Langs files for auto traduction of documentation                | :x:                   |
| Graphical interface                                             | :x:                   |
(to read the logs, see the result of the
auto detection, launch the operations,
select the auto detected elements to apply to the settings file,
modify the global,
auto detection or parameter settings, read the documentation etc)  
| Multithreading                                               	  | :x:                   |


### Global settings

| Name                                  		                                        | do ?                  |
| -------                               		                                        | ------------------    |
| global settings file 		                                                          | :x:                   |
| multithreading (true or false) 		                                                | :x:                   |
| thread count for:                                                                 | :x:                   |
(-1 = chosen by program, 0 = on main thread, 1-9 = split into a thread count)       
| thread count for: read content, transformation and writing of content, logs       | :x:                   |
| wholeWord default setting                                                         | :x:                   |
| caseSensitive default setting                                                     | :x:                   |
| directories                                                                       | :x:                   |
- source, destination,                                                 
- minecraft origines sources, minecraft destination sources,
- logs by types (error, warning, info, autodetection etc                             
| -------                               		                                        | ------------------    |
| logs level (error | warning | info)                                               | :x:                   |
| autodetection by names (true or false)                                            | :x:                   |
| autodetection by equivalences (true or false)                                     | :x:                   |
| autodetection logs (true or false)                                                | :x:                   |
| automatic application of auto detection in the settings_transformation.txt file   | :x:                   |
| % of necessary equivalence for auto detection                                     | :x:                   |
| prior origine sources transformation via settings file of the original sources    | :x:                   |
to obtain a closer equivalence (true or false)                                     
| automatic writing of documentation (true or false)                                | :x:                   |



### Transformations settings

| Name                                  		                                        | do ?                  |
| -------                               		                                        | ------------------    |
| transformations settings file 		                                                | :white_check_mark:    |
| use of double quotation marks for text 		                                        | :white_check_mark:    |
| Allow the use of quotes within quotes with a backslash despecialization           | :x:                   |
| Simplified lines ("world" = "level")                     			                    | :white_check_mark:    |
| Block with parameters                     			                                  | :white_check_mark:    |
	{
		« VoxelShape » = « Shapes »
		wholeword=true
		caseSensitive = adaptive
	}
| wholeWord setting (true or false)                    			                        | :white_check_mark:    |
| caseSensitive state (true, adaptive, false) and algorithm (simplified, complete)  | :white_check_mark:    |
