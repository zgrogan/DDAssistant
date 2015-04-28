# This file is still in development

# DDAssistant
## Description
DDAssistant is a project in development by UTPB Software Engineering students. The software will allow engineers and directional drillers to create target well curves and track survey information to provide information useful to meet drilling targets.

## Class Components
There are three categories the class components can fall into:
* GUI
   * DDWindow
     * The main top level window called after the main method, used to initialize all window components, and controls all information flowing from one class to another.
   * DDMenuBar
     * Contains the File Menu and MenuItems used to create, save, and load a DDWell Object.
     * Contains the Target Menu and MenuItems used to create a target well, set a new kickoff point, add a turn, and modifythe target window.
     * Contains the Help Menu and MenuItems with a link to the DDAssistant Wikia for help on operating DDAssistant.
   * DDMainPane
     * DDGraphPane
     * DDGraphControls
       * Initializes all Graph controls (Sliders, textboxes, etc) used to control the camera in DDGraph.
     * DDGraph
       * Displays the current well depth, target window, and current surveys in 3D. 
   * DDInformationPane
     * Contains a tabular structure with information about the current well being worked on.
     * Contains a way to add, edit, and delete surveys. 
* Processing
   * DDCurveData
     * Contains all of the math used to process and calculate where points must go onto the DDGraph.
   * DDWell
     * This is the main object that is used
* Network


## GUI Components
DDAssistant has 4 main components: Menu, Graph Control, Graph, and Information component
### Menu
The menu is used to create, save, and load a Well file,

## Help
DDAssistant Wikia: http://ddassistant.wikia.com/wiki/DDAssistant_Wiki
