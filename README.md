# World's War  

A virtual world with different species (ants, termites and spiders among others).  
The entities are autonomous (agents).  

## AUTHORS  
VI51 (Virtual Life Simulation) Project, UTBM (University of Technology of Belfort-Montbéliard)  

{firstname}.{lastname}[at]utbm.fr  

Belkacem Lahouel  
Maxime Bourgeois  
Romain Dorier  

## DIRECTORIES  
parser/ contains parsers and readers implemented  
env/ (stands for environment) contains the environment model  
gui/ contains the user interface (big-black-box style)  
sim/ (stands for simulator) contains the simulator "Application" class and agents  
info/ contains informations and manuals  
math/ contains general functions/methods implementing maths  
res/ (stands for resources) contains resources (mainly for the user interface) or eg. configuration files  

## TECHNICALITIES  
You may add an alias for this repository's address:  
git remote add github https://github.com/belkacemlahouel/WorldsWar  

Please put your Git repository in the source directory.  

With Eclipse IDE:  
(1) Create a new project.  
(2) Go to your EclipseProject/src/ directory.  
(3) Fetch (git pull) from the Github repository.  
	* git init  
	* git remote add github https://github.com/belkacemlahouel/WorldsWar  
	* git pull github master  
	* git br name.versionnumber.module // git branch  
	* git co name.versionnumber.module // git checkout  
	* Go to Eclipse, and refresh the project.  
(4) Work!  
(5) Push to the Github repository once it's good!  
	* git ci -m namefile "Message." OR git ci -am "Message" // git commit  
	* git push github branchname  
(6) If you want to update with the Github version.  
	* git pull github master  

Regarding Java versions, we will use Java 7 (JDK 1.7).  
We may use Java 8 (JDK 1.8) if you want to use multi-inheritance.  

## PUSHING RULES  
(1) Do not push in master for no good reason. 
(2) Work on you own branch.  
(3) Only push source code in Github (no executables!).  
(4) Try to document your methods.  
