SPOS converter
==============

Application, which converts route file, exported from 
[JRC ECDIS](http://www.jrc.co.jp/eng/product/lineup/jan9201_7201/index.html)
to route file,   
which is capable of being imported 
to [SPOS](https://www.meteogroup.com/product/spos)

![](https://scontent.fhen1-1.fna.fbcdn.net/v/t1.0-9/25508009_122364271888054_9049677626137106004_n.jpg?oh=bb119c90e7bad63d8cdf384682256042&oe=5AD5D37D)

Binary
------
**To launch application please use binary file, named *SPOSConverter.jar*.**  
You don't need any other files, bundled herewith to execute it.  
You are free to remove them or/and extract and use only *SPOSConverter.jar*.

**Please note, that [JRE](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html) should be installed on your PC to launch converter.**

Build example (linux)
-------------
All commands should be executed from the root directory of application
1. mkdir -p out/production/SPOSConverter
2. javac -sourcepath src/ -d out/production/SPOSConverter/ src/com/shubin/spos/converter/Main.java
3. cp -R src/com/shubin/spos/converter/View/ out/production/SPOSConverter/com/shubin/spos/converter/
4. jar -cfe MySPOSConverter.jar com.shubin.spos.converter.Main -C out/production/SPOSConverter/ com

Finally you receive your own build, named *MySPOSConverter.jar* in your root directory.

Test
-------
Application was tested on:
 - [JRC ECDIS](http://www.jrc.co.jp/eng/product/lineup/jan9201_7201/index.html) 
 App v. [01.20; 01.30](http://www.jrc.co.jp/eng/product/marine/navigation/ecdis_version.html)
 - [SPOS](https://www.meteogroup.com/product/spos) v. 8.3.5233.18431 Build: 30 Apr 2014 09:14UTC