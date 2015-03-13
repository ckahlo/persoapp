# Linux Packages #

The PersoApp-Project will provide linux packages for the following distributions:
  * Ubuntu/Debian
  * Fedora
  * Gentoo
  * Archlinux

The stable packages will be announced on the project web site https://www.persoapp.de.


# Testing/Unstable Packages #
Testing packages will be made available via [Linux Testing](https://drive.google.com/folderview?id=0BwAMd9a8dhRzU0dPdlRuNTNYams&usp=sharing).

## Instructions ##

### Debian/Ubuntu ###
  1. Download the .deb-file from [Linux Testing](https://drive.google.com/folderview?id=0BwAMd9a8dhRzU0dPdlRuNTNYams&usp=sharing)
  1. Install the dependencies:
```
sudo apt-get install openjdk-6-jre jarwrapper java-wrappers libpcsclite1
```
  1. Install the downloaded .deb-file either via a graphical installer or
```
sudo dpkg -i persoapp-XXX.deb
```

### Fedora ###
  1. Download the .rpm-file from [Linux Testing](https://drive.google.com/folderview?id=0BwAMd9a8dhRzU0dPdlRuNTNYams&usp=sharing)
  1. Install the dependencies:
```
sudo yum install java jpackage-utils pcsc-lite-libs pcsc-lite-devel
```
  1. Install the downloaded .rpm-file either via a graphical installer or
```
sudo rpm -i persoapp-XXX.rpm
```

You should make sure that ` pcscd ` is running and has recognized your card reader. Most card readers, including the inexpensive Reiner SCT CyberJack RFID basis, are supported by the CCID drivers:
```
sudo yum install ccid
```

After you have installed your drivers, you should start ` pcscd `:
```
sudo systemctl start pcscd
```

To make ` pcscd ` start on system start do:
```
sudo systemctl enable pcscd
```
### Gentoo ###

There are 2 ebuilds, version 1.0. is the prebuild jar file which will be installed on the computer. Version 9999 checks out the current development source code via subversion and compiles it. The later is to be used by developers or people who want to test the latest version of persoapp.

  1. Create a local portage overlay
```
mkdir -p /usr/local/overlays/local
```
    1. Create a configuration for the overlay
```
mkdir /usr/local/overlays/local/metadata
vi /usr/local/overlays/local/metadata/layout.conf
```
> > > Insert this as content:
```
masters = gentoo
```
> > > The overlay has to have a name, therefore:
```
mkdir /usr/local/overlays/local/profiles
vi /usr/local/overlays/local/profiles/repo_name
```
> > > Insert a name as content, for example ` persoapp_overlay `
  1. Create the category and persoapp directories
```
mkdir -p /usr/local/overlays/local/app-misc/persoapp
```
    1. Copy the ebuilds into this directory
```
cp persoapp-1.0.ebuild /usr/local/overlays/local/app-misc/persoapp/
```
    1. Create the manifest metadata for the ebuild(s)
```
cd /usr/local/overlays/local/app-misc/persoapp
ebuild persoapp-1.0.ebuild digest
```
  1. Integrate the local overlay into portage

> > Edit the ` /etc/make.conf ` and add
```
PORTDIR_OVERLAY="/usr/local/overlays/local"
```
  1. Install the app like any other gentoo software
```
emerge persoapp
```
  1. Start pcscd
```
/etc/init.d/pcscd start
```
  1. Start persoapp like any other programm
```
persoapp
```
### Archlinux ###
  1. Download the tar.gz-file from the Archlinux directory of [Linux Testing](https://drive.google.com/folderview?id=0BwAMd9a8dhRzU0dPdlRuNTNYams&usp=sharing).
  1. Extract the files from the archive
```
tar xzvf persoapp.tar.gz
```
  1. Create the Archlinux package
```
cd persoapp
makepkg -sc
```
  1. Install the package
```
sudo pacman -U persoapp-*.pkg.tar.xz
```

You should make sure that ` pcscd ` is running and has recognized your card reader. Most card readers, including the inexpensive Reiner SCT CyberJack RFID basis, are supported by the CCID drivers:
```
sudo pacman -S ccid
```
The more advanced ReinerSCT CyberJack need a custom driver, which is available from the Arch User Repository (AUR): [pcsc-cyberjack](https://aur.archlinux.org/packages/pcsc-cyberjack/).
After you have installed your drivers, you should start ` pcscd `:
```
sudo systemctl start pcscd
```

To make ` pcscd ` start on system start do:
```
sudo systemctl enable pcscd
```



## Running PersoApp ##
In general, PersoApp can be started from terminal or GUI-Starter (ALT+F2) by calling `persoapp`. If everything works fine, the persoapp-icon should appear in your desktop einvironment's systray. In case PersoApp does not start properly, please report an [Issue](https://code.google.com/p/persoapp/issues/list), which contains the distribution you're running, the persoapp package you installed and the console output when running `persoapp` from console.