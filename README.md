# To Do List
### Brief Description
: An individual project with Software Development Academy to create a To-Do List application.

### Goals
* Use Java to create a functioning To-Do List application
    * Core functionalities: application should allow user to add tasks (with a title and due date), assign tasks to a variety of projects, view tasks (both complete and incomplete), remove tasks, edit tasks, and mark tasks as complete; tasks should be sorted by date, project, or title; the user will interact with the system via a text-based user inteface via the commandline; the user must also be able to quit the application and save the state of the to-do list to a file that can be reloaded when the app is restarted. 
* Create sufficient documentation
* Create design documents as needed to facilitate project
* Design and implement unit testing

### Versions
* [First iteration](https://github.com/jshortz/SDA6ToDoList)
    * Scrapped due to a massive refactoring/restructure of the code
    * Very little work kept from this iteration (which never ran)
* [Second iteration](https://github.com/jshortz/SDAIPv2)
    * Scrapped due to build issues with Gradle/IntelliJ 
        * Most likely due to the project not being created properly in the first place
    * Needed refinement, but worked
    * Work kept and built on for final iteration
* [Final iteration](https://github.com/jshortz/SDAIndProjv2.1)
    * What you're looking at right now
    * Functionalities implemented, tests implemented, documentation added
    * Early reach goals of implementing a GUI and external links removed early in the process
    
### Reflection
* I started this project by making a class diagram and an activity diagram
* Before coding, I also created a series of milestones, one for each day, of what I would complete when 
* As I moved into actually coding the project, it became clear that: 
    * My class diagram would change... a lot
    * My activity diagram was more static
    * My milestones were hilariously optimistic
* My first iteration of the project was a huge failure and a huge learning opportunity
    * It never ran
    * I built everything all at once rather than implementing a method or so at a time
        * Which is why it never ran
        * I don't regret this, because I learned a lot about good practices from it
* There were some things that I thought would be enormously difficult that were actually quite simple (like sorting my lists)
    * There were also things that were more difficult than I anticipated (preventing duplicates)
* The one that got away:
    * Making a more friendly id for the user to enter
        * ID is currently the title of the Task plus a hash based on the due date
        * So if the title is really long, the ID will be really long as well
        * With more time, I'm sure I could have come up with something better
        * Though inelegant, this works as-is
* Areas for growth/practice:
    * More cohesion
    * Designing methods so that they're not reliant on user input
        * Have other methods that get the user input
        * This was a weakness I observed in particular with my unit tests
    * Making good decisions about whether to make a method/variable static/non-static 

### Timeline
* 09.30: Project documents released
    * 10.01: Finish README
    * 10.02: Draft scenarios, requirements index, and traceability matrix
* 10.03: Mentors assigned
    * 10.03: Draft use-case, activity, and sequence diagrams
    * 10.04: Draft class and object diagrams
    * 10.05: Review World of Zuul and build basic scanner interface
    * 10.06: Clean up scanner interface
    * 10.07: Implement task list
    * 10.08: Implement list mutation methods
    * 10.09: Implement sort methods
    * 10.10: Implement display methods
* 10.11: Midpoint review by TA
    * 10.11: Review TA feedback and incorporate changes
    * 10.12: Clean up implementation
    * 10.13: Clean up implementation
    * 10.14: Draft user manual in markdown
    * 10.15: Clean up project
    * 10.16: Implement tests and run them
    * 10.17: Implement tests and run them
    * 10.18-10.25: clean up and implement reach features
* 10.25: Final review by TA

### Project Document Links
[Project Checklist](https://docs.google.com/spreadsheets/d/1cQ6TJHUegOU0axuXaGG1ftL_whCpxxM35LN6zmTdtCo/edit#gid=470286829)
: Used for midpoint and final reviews

[Module Description](https://docs.google.com/document/d/1uwkcFO_e_uD85dYDYQ3ndPtqhtxMnRxgIcVj_GZzGVk/edit)
: Description of module, including learning objectives and forms of assessment

[Project Specification and Schedule](https://docs.google.com/document/d/1p6hDJFLd8cLC-uTCz-X8aLDKaee3z9Y9v15tkSm4XlM/edit)
: Technical specifications and schedule of assignment, midpoint, and end phases

### Resources
* As a very junior developer, I relied on a number of resources in building this project - I've tried to collect them below
1. [link](https://www.mkyong.com/java/java-how-to-save-a-string-to-a-file/) : I used this to review how to save information to files with java.nio
2. [Stack Overflow](https://stackoverflow.com/questions) : I used Stack Overflow to solve a variety of small problems