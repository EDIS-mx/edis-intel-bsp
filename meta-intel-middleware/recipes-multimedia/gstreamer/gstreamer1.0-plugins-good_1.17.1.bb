require gstreamer1.0-plugins-common.inc

SRC_URI = " \
            gitsm://github.com/GStreamer/gst-plugins-good.git;protocol=https \
            file://0001-Fix-for-Qt-build-issue-with-sysroot-on-latest-compil.patch \
            "
SRCREV = "03ac058183791ec301ee8b45840e4e38b6dcf929"

S = "${WORKDIR}/git"

LICENSE = "GPLv2+ & LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://gst/replaygain/rganalysis.c;beginline=1;endline=23;md5=b60ebefd5b2f5a8e0cab6bfee391a5fe"

DEPENDS += "gstreamer1.0-plugins-base libcap zlib"
RPROVIDES_${PN}-pulseaudio += "${PN}-pulse"
RPROVIDES_${PN}-soup += "${PN}-souphttpsrc"

PACKAGECONFIG ??= " \
    ${GSTREAMER_ORC} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'pulseaudio x11', d)} \
    bz2 cairo flac gdk-pixbuf gudev jpeg lame libpng mpg123 soup speex taglib v4l2 \
"

X11DEPENDS = "virtual/libx11 libsm libxrender libxfixes libxdamage"
X11ENABLEOPTS = "-Dximagesrc=enabled -Dximagesrc-xshm=enabled -Dximagesrc-xfixes=enabled -Dximagesrc-xdamage=enabled"
X11DISABLEOPTS = "-Dximagesrc=disabled -Dximagesrc-xshm=disabled -Dximagesrc-xfixes=disabled -Dximagesrc-xdamage=disabled"

PACKAGECONFIG[bz2]        = "-Dbz2=enabled,-Dbz2=disabled,bzip2"
PACKAGECONFIG[cairo]      = "-Dcairo=enabled,-Dcairo=disabled,cairo"
PACKAGECONFIG[dv1394]     = "-Ddv1394=enabled,-Ddv1394=disabled,libiec61883 libavc1394 libraw1394"
PACKAGECONFIG[flac]       = "-Dflac=enabled,-Dflac=disabled,flac"
PACKAGECONFIG[gdk-pixbuf] = "-Dgdk-pixbuf=enabled,-Dgdk-pixbuf=disabled,gdk-pixbuf"
PACKAGECONFIG[gtk]        = "-Dgtk3=enabled,-Dgtk3=disabled,gtk+3"
PACKAGECONFIG[gudev]      = "-Dv4l2-gudev=enabled,-Dv4l2-gudev=disabled,libgudev"
PACKAGECONFIG[jack]       = "-Djack=enabled,-Djack=disabled,jack"
PACKAGECONFIG[jpeg]       = "-Djpeg=enabled,-Djpeg=disabled,jpeg"
PACKAGECONFIG[lame]       = "-Dlame=enabled,-Dlame=disabled,lame"
PACKAGECONFIG[libpng]     = "-Dpng=enabled,-Dpng=disabled,libpng"
PACKAGECONFIG[libv4l2]    = "-Dv4l2-libv4l2=enabled,-Dv4l2-libv4l2=disabled,v4l-utils"
PACKAGECONFIG[mpg123]     = "-Dmpg123=enabled,-Dmpg123=disabled,mpg123"
PACKAGECONFIG[pulseaudio] = "-Dpulse=enabled,-Dpulse=disabled,pulseaudio"
PACKAGECONFIG[qt5]        = "-Dqt5=enabled,-Dqt5=disabled,qtbase qtdeclarative qtbase-native"
PACKAGECONFIG[soup]       = "-Dsoup=enabled,-Dsoup=disabled,libsoup-2.4"
PACKAGECONFIG[speex]      = "-Dspeex=enabled,-Dspeex=disabled,speex"
PACKAGECONFIG[taglib]     = "-Dtaglib=enabled,-Dtaglib=disabled,taglib"
PACKAGECONFIG[v4l2]       = "-Dv4l2=enabled -Dv4l2-probe=true,-Dv4l2=disabled -Dv4l2-probe=false"
PACKAGECONFIG[vpx]        = "-Dvpx=enabled,-Dvpx=disabled,libvpx"
PACKAGECONFIG[wavpack]    = "-Dwavpack=enabled,-Dwavpack=disabled,wavpack"
PACKAGECONFIG[x11]        = "${X11ENABLEOPTS},${X11DISABLEOPTS},${X11DEPENDS}"

EXTRA_OEMESON += " \
    -Daalib=disabled \
    -Ddirectsound=disabled \
    -Ddv=disabled \
    -Dlibcaca=disabled \
    -Doss=enabled \
    -Doss4=disabled \
    -Dosxaudio=disabled \
    -Dosxvideo=disabled \
    -Dshout2=disabled \
    -Dtwolame=disabled \
    -Dwaveform=disabled \
"
EXTRA_OEMESON_remove = "--disable-qt"

FILES_${PN}-equalizer += "${datadir}/gstreamer-1.0/presets/*.prs"
