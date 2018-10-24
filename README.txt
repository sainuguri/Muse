Hello!

This code was a part of our vSocial project at University of Missouri, Columbia wherein we create an objective way of data collection from students with Autism via an EEG headset (Muse). 

---------

The Muse Tools use Open Sound Control (OSC) to pass data around. OSC is a simple protocol for sending data over a network. It was originally intended as a successor to MIDI, the well-known protocol for controlling electronic instruments, but it turns out to be really useful for all sorts of things, including Muse data.

If you’ve followed the Tools Getting Started guide for your OS you know that the way things work is like this: you connect to Muse with Muse Direct or MuseIO and then the tool you connected with starts sending out tons of data over OSC. In the Getting Started, the next step was to run MuseLab to receive and graph it all.

But what if you want to get that OSC data into your own program? You need an OSC server – something to listen for and handle incoming messages. Luckily, there are OSC libraries for seemingly every major programming language, so you just need to find the right one and include it in your project.

---------

Firstly, you need to connect to your Muse with Muse Direct.
Next, on Muse Direct, add another output stream by clicking on "Add" on the "Output to" page and write in the ip-address of the system running the server and the port it is listening to, such as, 5000 or 7000.
Finally, make sure your server(the program in this repo) is litening to the same port, and run the program to start receiving and parsing messages.

To understand how to connect your Muse headset to Muse Direct, visit: http://developer.choosemuse.com/tools/windows-tools/musedirect

------


Please contact Sai Shreya Nuguri at saisn.67@gmail.com or Dr. Calyam at calyamp@missouri.edu for any questions or suggestions.

Thank you! 