# Challenges Instructions

## Introduction
Hello and welcome to the challenges. I have been working on these challenges for about a month now, and they are finally done. These are REQUIRED if you want to write code on the robot this year. They are designed to test your knowledge of Java and the WPILib library. If you have any questions, feel free to ask me. I will be more than happy to help you.

You must read the instructions for each challenge carefully. They will tell you what you need to do. There are 6 challenges in total, and you may complete them in any order (but they are sorted in ascending difficulty, so I recommend you do them in order). You may use any resources you want to complete the challenges including Google, StackOverflow, Chief Delphi, and the WPILib documentation, **but you may NOT use generative AI such as ChatGPT**. Trust me when I say, it does not know modern robot code that well and will not help you. Also, don't ask your friends for help. I want to see what you can do, not what your friends can do.

## Instructions
### Setting Up the Repository
1. Fork this repository to your own account. You can do this by clicking the "Fork" button in the top right corner, right next to "Watch" and "Star". Make sure you un-check the box that says "Copy the main branch only" so that you can get all of the challenges properly.
2. Clone the repository to your computer. You can do this by clicking the green "Code" button and copying the URL. Then, open a terminal and run `git clone <URL>` to copy it to the current folder you are in (switch to your coding folder before you do this).
4. Open VSCode and open the folder you just cloned. You should see this README file.

### Beginning a Challenge
1. Each challenge already has its own branch. To start a challenge, you must first switch to the branch for that challenge. You can do this by running `git checkout <branch-name>`. For example, to switch to the first challenge, you would run `git checkout challenge1`.
2. Read the README file for that challenge. It will tell you exactly what you need to do, so read it carefully. If you press `CTRL+SHIFT+V` (or `Command+Shift+V`) when the README is open in the editor (or if you right-click on the README in the file list and select "Open Preview") you can preview it with formatting so it looks nicer. You can also switch branches on the GitHub website and scroll down to the README for that challenge.
3. Complete the challenge as described in the README file. This will vary for each challenge.
4. When you are done, build the code with the Gradle build task. You can do this by pressing `Ctrl+Shift+P` (or `Command+Shift+P`) and typing "Build Robot Code". Then, press Enter. If this does not work, you can also run `./gradlew build` (or `./gradlew.bat build` if on Windows) in the terminal. Unit tests have been set up to check if your code works, so if the build fails, you will need to fix your code. There will usually be some kind of message that tells you what part of your code failed.
5. If the build is successful, commit your changes and push them to your repository. You can do this by running `git add .`, `git commit -m "<message>"`, and `git push origin <branch-name>`. For example, to push your changes to the first challenge, you would run `git push origin challenge1`.
6. Go to your GitHub repository and verify that the changes have been pushed. When you push, it will run a build task. If this build task is successful, you are now done with the challenge. If it fails despite working on your computer, you may not have pushed all of your changes. If it fails and you don't know why, feel free to ask me for help.
7. Once you get a green checkmark next to your commit name, you are done with the challenge, and can move onto the next one. Tell me when you finish a challenge, and I will review it. I will mainly look at the completed tests, but I will also look at the code to make sure you did it correct, and I will give you pointers on anything you can improve.

## Tips for Completing Challenges
- **Read the WPILib documentation if you don't know how to do something.** This is the most important resource you have. It will tell you how to use the WPILib library and how to program the robot. They have a lot of examples that you can use.
- **Read the instructions carefully.** I cannot stress this enough. The instructions will tell you what you need to do, and what files to modify. If you don't understand something, ask me, but I will not answer questions that are already answered in the instructions.
- **Don't be afraid to ask me for help.** I am more than happy to help you with the challenges. If you don't understand something, ask me. I will not give you the answer, but I will help you understand the challenge better.

## Conclusion
Even though the challenges are required, I hope you have fun with them. They are not designed to be impossibly difficult or overly tedious, but rather to test your knowledge and determine if you are ready to write code for a real robot. Good luck, and happy coding!
