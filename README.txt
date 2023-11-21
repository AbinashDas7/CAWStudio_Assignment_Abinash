README

Basic Info:

So I have used Eclipse as my IDE
Used Selenium webdriver
Used Java as the language
Used TestNg framework to run and generate test suited
Used Cucumber and BDD in feature file


==========================================================================================================


Access related:

-You can get my json file from here : (datafile.json)
TestingBrowser/src/test/resources/resource/datafile.json

-You can get my Feature file from
TestingBrowser/src/test/resources/feature/tableBrowser.feature

-StepDefinition file is located:
TestingBrowser/src/test/java/Code/stepDefinition.java

-Main code is located : TestingBrowser/src/test/java/Code/mainCode.java

-Screenshot stored inside: TestingBrowser/src/test/java/Screenshots/


==========================================================================================================

How to run :

Just go to : TestingBrowser/src/test/java/TestRunner/RunnerTest.java
use TestNg to generate test suite details...
Run it.

On console you can see if there is any duplicate value exist or not
If exist, Then it will remove it and reenter it accordingly

console:(adding duplicate):

{"name" : "Bob", "age" : 20, "gender": "male"},
{"name" : "Bob", "age" : 20, "gender": "male"},
{"name": "George", "age" : 42, "gender": "male"}, 
{"name":"Sara", "age" : 42, "gender": "female"}, 
{"name": "Conor", "age" : 40, "gender": "male"},
{"name":"Jennifer", "age" : 42, "gender": "female"}]
entering into clickOnRefreshButton()..
entering into validateResults()..
having name Bob
having name Bob
having name George
having name Sara
having name Conor
having name Jennifer
final names from ui are Bob Bob George Sara Conor Jennifer

final after removing duplicate: 
[{"gender":"male","name":"Bob","age":20},
{"gender":"male","name":"George","age":42},
{"gender":"female","name":"Sara","age":42},
{"gender":"male","name":"Conor","age":40},
{"gender":"female","name":"Jennifer","age":42}]

