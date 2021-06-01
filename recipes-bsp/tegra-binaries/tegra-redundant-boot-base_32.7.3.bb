DESCRIPTION = "L4T bootloader update support tools"
L4T_DEB_COPYRIGHT_MD5 = "fe65a18c0cbf70d19e3d6202a8c825b6"

L4T_DEB_TRANSLATED_BPN = "nvidia-l4t-tools"
TEGRA_LIBRARIES_CONTAINER_CSV = ""

require tegra-debian-libraries-common.inc

MAINSUM = "43932abc259dff43cbf1973755672682746bc606d7b51c7f4a6c691df1ccb76c"
MAINSUM_tegra210 = "f0974413fa479067a5207d36ffa8b9c42cf3bd3f9c605bfdfa57ef4cfd940034"

SRC_URI_append_tegra210 = " file://Convert-l4t_payload_updater_t210-to-Python3-R32.7.1.patch"

do_install() {
	install -d ${D}${sbindir}
	install -m 0755 ${S}/usr/sbin/nvbootctrl ${D}${sbindir}
	install -m 0755 ${S}/usr/sbin/nv_bootloader_payload_updater ${D}${sbindir}
	install -m 0755 ${S}/usr/sbin/nv_update_engine ${D}${sbindir}
	install -d ${D}/opt/ota_package
}

do_install_tegra210() {
	install -d ${D}${sbindir}
	install -m 0755 ${S}/usr/sbin/l4t_payload_updater_t210 ${D}${sbindir}
	install -d ${D}/opt/ota_package
}

PACKAGES = "tegra-redundant-boot-nvbootctrl ${PN} ${PN}-dev"
FILES_tegra-redundant-boot-nvbootctrl = "${sbindir}/nvbootctrl"
FILES_${PN} += "/opt/ota_package"
RDEPENDS_${PN} = "tegra-redundant-boot-nvbootctrl setup-nv-boot-control-service tegra-configs-bootloader"
RDEPENDS_${PN}_tegra210 = "setup-nv-boot-control-service python3-core"
INSANE_SKIP_${PN} = "ldflags"
RDEPENDS_tegra-redundant-boot-nvbootctrl = "setup-nv-boot-control"
RDEPENDS_tegra-redundant-boot-nvbootctrl_tegra210 = ""
ALLOW_EMPTY_tegra-redundant-boot-nvbootctrl_tegra210 = "1"
INSANE_SKIP_tegra-redundant-boot-nvbootctrl = "ldflags"