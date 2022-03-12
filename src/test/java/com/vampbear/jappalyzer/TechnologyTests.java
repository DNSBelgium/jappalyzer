package com.vampbear.jappalyzer;

import org.junit.Test;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.*;

public class TechnologyTests {

    private static final String TECHNOLOGY_STRING = "{\n" +
            "    \"cats\": [\n" +
            "      31\n" +
            "    ],\n" +
            "    \"description\": \"DPD is an international parcel delivery service for sorter compatible parcels.\", " +
            "    \"cookies\": {\n" +
            "      \"trbo_session\": \"^(?:[\\\\d]+)$\",\n" +
            "      \"django_language\": \"\"" +
            "    }," +
            "    \"headers\": {\n" +
            "      \"Derak-Umbrage\": \"\",\n" +
            "      \"Server\": \"^DERAK\\\\.CLOUD$\"\n" +
            "    },\n" +
            "    \"icon\": \"DerakCloud.png\",\n" +
            "    \"js\": {\n" +
            "      \"derakCloud.init\": \"\"\n" +
            "    },\n" +
            "    \"website\": \"https://derak.cloud\"\n" +
            "  }";

    public static final String JOOMLA_TECH_STRING = "{\n" +
            "    \"cats\": [\n" +
            "      1\n" +
            "    ],\n" +
            "    \"cpe\": \"cpe:/a:joomla:joomla\",\n" +
            "    \"description\": \"Joomla is a free and open-source content management system for publishing web content.\",\n" +
            "    \"headers\": {\n" +
            "      \"X-Content-Encoded-By\": \"Joomla! ([\\\\d.]+)\\\\;version:\\\\1\"\n" +
            "    },\n" +
            "    \"html\": \"(?:<div[^>]+id=\\\"wrapper_r\\\"|<(?:link|script)[^>]+(?:feed|components)/com_|<table[^>]+class=\\\"pill)\\\\;confidence:50\",\n" +
            "    \"icon\": \"Joomla.svg\",\n" +
            "    \"implies\": \"PHP\",\n" +
            "    \"js\": {\n" +
            "      \"Joomla\": \"\",\n" +
            "      \"jcomments\": \"\"\n" +
            "    },\n" +
            "    \"meta\": {\n" +
            "      \"generator\": \"Joomla!(?: ([\\\\d.]+))?\\\\;version:\\\\1\"\n" +
            "    },\n" +
            "    \"oss\": true,\n" +
            "    \"url\": \"option=com_\",\n" +
            "    \"website\": \"https://www.joomla.org\"\n" +
            "  }";

    public static final String JOOMLA_META_CONTENT = "<!DOCTYPE html>\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"fa-ir\" lang=\"fa-ir\" dir=\"rtl\">\n" +
            "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
            "\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "\n" +
            "\n" +
            "    <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />\n" +
            "\t<meta name=\"keywords\" content=\"هاست لینوکس,هاست ایران,هاست,هاست خارجی,خرید هاست,ثبت ارزان دامنه,هاست ایرانی , هاست ویندوز\" />\n" +
            "\t<meta name=\"description\" content=\"بهترین کیفیت و قیمت خرید هاست لینوکس و هاست ایران را تجربه کنید همراه با تحویل آنی و ثبت ارزان دامنه با پشتیبانی 24 ساعته\" />\n" +
            "\t<meta name=\"generator\" content=\"Joomla! - Open Source Content Management\" />\n" +
            "\t<title>هاست لینوکس | هاست ایران | هاست ویندوز | ثبت ارزان دامنه | هاست نگار</title>\n" +
            "\t<link href=\"/favicon.ico\" rel=\"shortcut icon\" type=\"image/vnd.microsoft.icon\" />" +
            "</head><body></body></html>";

    private static final String FONT_AWESOME_CONTENT = "<section id=\"pixel-perfect\" class=\"pb6 ph6 ph7-l\"><link href=\"https://kit-pro.fontawesome.com/releases/v5.15.4/css/pro.min.css\" rel=\"stylesheet\"> <div class=\"bt bw2 b--gray1 pt6 mw9 center\"><article class=\"flex flex-column flex-row-xl flex-nowrap-xl items-stretch-xl nr6 nb4\"><div class=\"pr6 pb4 w-40-xl\"><h2 class=\"mt0 mb2 f5 fw6\">Professionally Designed + Pixel-Perfect</h2> <div class=\"f4-l lh-copy\"><p class=\"mv0\">Each and every symbol is designed from scratch against guidelines and standards forged from years of experience of illustrating and designing icons. The result is a consistent look and feel that spans thousands of icons across four unique styles – with <a href=\"/v6.0\" class=\"link color-inherit link-underline-dark hover-primary6\">more coming in v6</a>.</p></div> <a href=\"/v5.15/icons?d=gallery&amp;p=1\" class=\"mt4 dn dib-xl link button-depth fw6 ba b--black-20 br2 ph5 pv3 hover-b--black-30 tc near-white nowrap hover-white bg-primary6 hover-bg-primary7\"><svg aria-hidden=\"true\" focusable=\"false\" data-prefix=\"fas\" data-icon=\"binoculars\" role=\"img\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 512 512\" class=\"mr2 svg-inline--fa fa-binoculars fa-w-16 fa-lg\"><path fill=\"currentColor\" d=\"M416 48c0-8.84-7.16-16-16-16h-64c-8.84 0-16 7.16-16 16v48h96V48zM63.91 159.99C61.4 253.84 3.46 274.22 0 404v44c0 17.67 14.33 32 32 32h96c17.67 0 32-14.33 32-32V288h32V128H95.84c-17.63 0-31.45 14.37-31.93 31.99zm384.18 0c-.48-17.62-14.3-31.99-31.93-31.99H320v160h32v160c0 17.67 14.33 32 32 32h96c17.67 0 32-14.33 32-32v-44c-3.46-129.78-61.4-150.16-63.91-244.01zM176 32h-64c-8.84 0-16 7.16-16 16v48h96V48c0-8.84-7.16-16-16-16zm48 256h64V128h-64v160z\" class=\"\"></path></svg>\n" +
            "          See All 7,864 Icons\n" +
            "        </a></div> <div class=\"pr6 pb4 w-60-xl\"><div class=\"iconStyleShowcase relative\"><ul class=\"dn-l list ma0 pa0 flex flex-row flex-wrap justify-start items-stretch bg-white pa4 br2 shadow-1\"><li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/band-aid?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-band-aid fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/baseball?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-baseball fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/box-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-box-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-l bb bw1 b--primary1\"><a href=\"/v5.15/icons/child?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-child fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/church?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-church fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/feather-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-feather-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/save?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-save fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 bb bw1 b--primary1\"><a href=\"/v5.15/icons/drafting-compass?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-drafting-compass fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/vial?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-vial fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/fish?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-fish fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/user-ninja?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-user-ninja fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-l bb bw1 b--primary1\"><a href=\"/v5.15/icons/concierge-bell?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-concierge-bell fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/tooth?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-tooth fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/sign?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-sign fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/forklift?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-forklift fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-0 bb bw1 b--primary1\"><a href=\"/v5.15/icons/file-prescription?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-file-prescription fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/wine-glass-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-wine-glass-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/boxing-glove?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-boxing-glove fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/couch?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-couch fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-l bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/mortar-pestle?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-mortar-pestle fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/dna?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-dna fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/bus-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-bus-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/car?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-car fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-0 bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/stethoscope?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-stethoscope fa-2x\"></i></a></li></ul> <ul class=\"icons icons-1 list ma0 pa0 flex flex-row flex-wrap justify-start items-stretch absolute left-0 top-0 w-100 bg-white pa4 br2 shadow-1\"><li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/band-aid?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-band-aid fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/baseball?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-baseball fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/box-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-box-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-l bb bw1 b--primary1\"><a href=\"/v5.15/icons/child?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-child fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/church?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-church fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/feather-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-feather-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/save?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-save fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 bb bw1 b--primary1\"><a href=\"/v5.15/icons/drafting-compass?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-drafting-compass fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/vial?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-vial fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/fish?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-fish fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/user-ninja?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-user-ninja fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-l bb bw1 b--primary1\"><a href=\"/v5.15/icons/concierge-bell?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-concierge-bell fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/tooth?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-tooth fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/sign?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-sign fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/forklift?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-forklift fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-0 bb bw1 b--primary1\"><a href=\"/v5.15/icons/file-prescription?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-file-prescription fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/wine-glass-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-wine-glass-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/boxing-glove?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-boxing-glove fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/couch?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-couch fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-l bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/mortar-pestle?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-mortar-pestle fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/dna?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-dna fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/bus-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-bus-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/car?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-car fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-0 bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/stethoscope?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"far fa-stethoscope fa-2x\"></i></a></li></ul> <ul class=\"icons icons-2 list ma0 pa0 flex flex-row flex-wrap justify-start items-stretch absolute left-0 top-0 w-100 bg-white pa4 br2 shadow-1\"><li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/band-aid?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-band-aid fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/baseball?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-baseball fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/box-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-box-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-l bb bw1 b--primary1\"><a href=\"/v5.15/icons/child?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-child fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/church?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-church fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/feather-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-feather-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/save?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-save fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 bb bw1 b--primary1\"><a href=\"/v5.15/icons/drafting-compass?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-drafting-compass fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/vial?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-vial fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/fish?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-fish fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/user-ninja?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-user-ninja fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-l bb bw1 b--primary1\"><a href=\"/v5.15/icons/concierge-bell?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-concierge-bell fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/tooth?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-tooth fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/sign?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-sign fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/forklift?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-forklift fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-0 bb bw1 b--primary1\"><a href=\"/v5.15/icons/file-prescription?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-file-prescription fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/wine-glass-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-wine-glass-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/boxing-glove?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-boxing-glove fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/couch?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-couch fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-l bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/mortar-pestle?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-mortar-pestle fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/dna?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-dna fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/bus-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-bus-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/car?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-car fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-0 bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/stethoscope?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fal fa-stethoscope fa-2x\"></i></a></li></ul> <ul class=\"icons icons-3 list ma0 pa0 flex flex-row flex-wrap justify-start items-stretch absolute left-0 top-0 w-100 bg-white pa4 br2 shadow-1\"><li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/band-aid?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-band-aid fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/baseball?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-baseball fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/box-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-box-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-l bb bw1 b--primary1\"><a href=\"/v5.15/icons/child?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-child fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/church?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-church fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/feather-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-feather-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/save?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-save fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 bb bw1 b--primary1\"><a href=\"/v5.15/icons/drafting-compass?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-drafting-compass fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/vial?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-vial fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/fish?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-fish fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/user-ninja?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-user-ninja fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-l bb bw1 b--primary1\"><a href=\"/v5.15/icons/concierge-bell?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-concierge-bell fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/tooth?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-tooth fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/sign?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-sign fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/forklift?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-forklift fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-0 bb bw1 b--primary1\"><a href=\"/v5.15/icons/file-prescription?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-file-prescription fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/wine-glass-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-wine-glass-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/boxing-glove?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-boxing-glove fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/couch?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-couch fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-l bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/mortar-pestle?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-mortar-pestle fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/dna?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-dna fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/bus-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-bus-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/car?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-car fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-0 bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/stethoscope?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fad fa-stethoscope fa-2x\"></i></a></li></ul> <ul class=\"icons icons-4 list ma0 pa0 flex flex-row flex-wrap justify-start items-stretch absolute left-0 top-0 w-100 bg-white pa4 br2 shadow-1\"><li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/band-aid?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-band-aid fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/baseball?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-baseball fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/box-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-box-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-l bb bw1 b--primary1\"><a href=\"/v5.15/icons/child?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-child fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/church?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-church fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/feather-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-feather-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/save?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-save fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 bb bw1 b--primary1\"><a href=\"/v5.15/icons/drafting-compass?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-drafting-compass fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/vial?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-vial fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/fish?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-fish fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/user-ninja?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-user-ninja fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-l bb bw1 b--primary1\"><a href=\"/v5.15/icons/concierge-bell?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-concierge-bell fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/tooth?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-tooth fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/sign?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-sign fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bw1 b--primary1\"><a href=\"/v5.15/icons/forklift?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-forklift fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-0 bb bw1 b--primary1\"><a href=\"/v5.15/icons/file-prescription?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-file-prescription fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/wine-glass-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-wine-glass-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/boxing-glove?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-boxing-glove fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/couch?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-couch fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-l bb bb-0-l bw1 b--primary1\"><a href=\"/v5.15/icons/mortar-pestle?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-mortar-pestle fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/dna?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-dna fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/bus-alt?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-bus-alt fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/car?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-car fa-2x\"></i></a></li> <li class=\"ma0 pa0 w-grid-4 w-grid-8-l ph4 pv3 br-0 bb-0 bw1 b--primary1\"><a href=\"/v5.15/icons/stethoscope?style=solid\" class=\"w-100 link color-inherit hover-primary6 flex flex-column justify-center items-center\"><i class=\"fas fa-stethoscope fa-2x\"></i></a></li></ul> <a href=\"/v5.15/icons?d=gallery&amp;p=1\" class=\"mt4 db w-100 w-auto-ns dib-ns dn-xl link button-depth fw6 ba b--black-20 br2 ph5 pv3 hover-b--black-30 tc near-white nowrap hover-white bg-primary6 hover-bg-primary7\"><svg aria-hidden=\"true\" focusable=\"false\" data-prefix=\"fas\" data-icon=\"binoculars\" role=\"img\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 512 512\" class=\"mr2 svg-inline--fa fa-binoculars fa-w-16 fa-lg\"><path fill=\"currentColor\" d=\"M416 48c0-8.84-7.16-16-16-16h-64c-8.84 0-16 7.16-16 16v48h96V48zM63.91 159.99C61.4 253.84 3.46 274.22 0 404v44c0 17.67 14.33 32 32 32h96c17.67 0 32-14.33 32-32V288h32V128H95.84c-17.63 0-31.45 14.37-31.93 31.99zm384.18 0c-.48-17.62-14.3-31.99-31.93-31.99H320v160h32v160c0 17.67 14.33 32 32 32h96c17.67 0 32-14.33 32-32v-44c-3.46-129.78-61.4-150.16-63.91-244.01zM176 32h-64c-8.84 0-16 7.16-16 16v48h96V48c0-8.84-7.16-16-16-16zm48 256h64V128h-64v160z\" class=\"\"></path></svg>\n" +
            "          See All 7,864 Icons\n" +
            "        </a></div></div></article></div></section>";

    private static final String PAGE_WITH_SCRIPT = "<html>" +
            "<head>" +
            "<script src=\"/vendor/livewire/livewire.js?id=83b555bb3e243bc25f35\"></script>" +
            "</head>" +
            "</html>";

    public static final String META_KEY_TECH = "{" +
            "\"meta\": {\n" +
            "      \"pjax-push\": \"\",\n" +
            "      \"pjax-replace\": \"\",\n" +
            "      \"pjax-timeout\": \"\"\n" +
            "    }" +
            "}";

    public static final String META_KEY_CONTENT = "" +
            "<html><head>\n" +
            "\t<meta name=\"pjax-push\" content=\"Some unimportant content\" />\n" +
            "</head><body></body></html>";

    public static final String CATS_TECH = "" +
            "{\n" +
            "    \"cats\": [\n" +
            "      41,\n" +
            "      91\n" +
            "    ],\n" +
            "    \"description\": \"PacePay offers a BNPL (Buy now pay later) solution for merchants.\",\n" +
            "    \"icon\": \"Pace.svg\",\n" +
            "    \"js\": {\n" +
            "      \"pacePay\": \"\"\n" +
            "    },\n" +
            "    \"saas\": true,\n" +
            "    \"scriptSrc\": \"pay\\\\.pacenow\\\\.co\",\n" +
            "    \"website\": \"https://pacenow.co/\"\n" +
            "}";

    public static final String PRICING_SAAS_TECH = "{\n" +
            "    \"cats\": [\n" +
            "      16\n" +
            "    ],\n" +
            "    \"description\": \"Jumio is an online mobile payments and identity verification company that provides card and ID scanning and validation products for mobile and web transactions.\",\n" +
            "    \"icon\": \"Jumio.svg\",\n" +
            "    \"dom\": \"iframe[src*='.netverify.com/']\",\n" +
            "    \"saas\": true,\n" +
            "    \"pricing\": [\n" +
            "      \"payg\",\n" +
            "      \"mid\",\n" +
            "      \"recurring\"\n" +
            "    ],\n" +
            "    \"website\": \"https://www.jumio.com\"\n" +
            "  }";

    public static final String META_GENERATOR_LIST_TECH = "{\n" +
            "    \"cats\": [\n" +
            "      6\n" +
            "    ],\n" +
            "    \"description\": \"Abicart is an ecommerce platform developed by the Swedish company Abicart AB.\",\n" +
            "    \"icon\": \"abicart.png\",\n" +
            "    \"meta\": {\n" +
            "      \"generator\": [\n" +
            "        \"Abicart\",\n" +
            "        \"Textalk Webshop\"\n" +
            "      ]\n" +
            "    }" +
            "}";

    @Test
    public void shouldMatchFontAwesome() {
        Technology technology = new Technology("Font Awesome");
        technology.addHtmlTemplate("<link[^>]* href=[^>]+(?:([\\d.]+)/)?(?:css/)?font-awesome(?:\\.min)?\\.css\\;version:\\1");
        technology.addHtmlTemplate("<link[^>]* href=[^>]*kit\\-pro\\.fontawesome\\.com/releases/v([0-9.]+)/\\;version:\\1");
        TechnologyMatch expected = new TechnologyMatch(technology, TechnologyMatch.HTML);
        assertThat(technology.applicableTo(FONT_AWESOME_CONTENT)).isEqualTo(expected);
    }

    @Test
    public void emptyHeaderTest() {
        Technology technology = new Technology("test");
        technology.addHeaderTemplate("X-Flex-Lang", "");
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("X-Flex-Lang", Collections.singletonList("IT"));
        PageResponse pageResponse = new PageResponse(200, headers, "");
        TechnologyMatch expected = new TechnologyMatch(technology, TechnologyMatch.HEADER);
        assertThat(technology.applicableTo(pageResponse)).isEqualTo(expected);
    }

    @Test
    public void emptyHeaderPageLowerCaseTest() {
        Technology technology = new Technology("test");
        technology.addHeaderTemplate("X-Flex-Lang", "");
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("x-flex-lang", Collections.singletonList("IT"));
        PageResponse pageResponse = new PageResponse(200, headers, "");
        TechnologyMatch expected = new TechnologyMatch(technology, TechnologyMatch.HEADER);
        assertThat(technology.applicableTo(pageResponse)).isEqualTo(expected);
    }

    @Test
    public void emptyHeaderTechnologyLowerCaseTest() {
        Technology technology = new Technology("test");
        technology.addHeaderTemplate("x-flex-lang", "");
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("X-Flex-Lang", Collections.singletonList("IT"));
        PageResponse pageResponse = new PageResponse(200, headers, "");
        TechnologyMatch expected = new TechnologyMatch(technology, TechnologyMatch.HEADER);
        assertThat(technology.applicableTo(pageResponse)).isEqualTo(expected);
    }

    @Test
    public void emptyCookieTechnologyTest() {
        Technology technology = new Technology("test");
        technology.addCookieTemplate("forterToken", "");
        PageResponse pageResponse = new PageResponse(200, null, "");
        pageResponse.addCookie("forterToken", "");
        TechnologyMatch expected = new TechnologyMatch(technology, TechnologyMatch.COOKIE);
        assertThat(technology.applicableTo(pageResponse)).isEqualTo(expected);
    }

    @Test
    public void serverHeaderTest() {
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Server", Collections.singletonList("nginx"));
        PageResponse pageResponse = new PageResponse(200, headers, "");
        Technology technology = new Technology("Nginx");
        technology.addHeaderTemplate("Server", "nginx(?:/([\\d.]+))?\\;version:\\1");
        TechnologyMatch expected = new TechnologyMatch(technology, TechnologyMatch.HEADER);
        assertThat(technology.applicableTo(pageResponse)).isEqualTo(expected);
    }

    @Test
    public void cookieHeaderTest() {
        Technology technology = new Technology("Trbo");
        technology.addCookieTemplate("trbo_session", "^(?:[\\d]+)$");
        PageResponse pageResponse = new PageResponse(200, null, "");
        pageResponse.addCookie("trbo_session", "12312312");
        TechnologyMatch expected = new TechnologyMatch(technology, TechnologyMatch.COOKIE);
        assertThat(technology.applicableTo(pageResponse)).isEqualTo(expected);
    }

    @Test
    public void scriptTest() {
        Technology technology = new Technology("test");
        technology.addScriptSrc("livewire(?:\\.min)?\\.js");
        PageResponse pageResponse = new PageResponse(200, null, PAGE_WITH_SCRIPT);
        TechnologyMatch expected = new TechnologyMatch(technology, TechnologyMatch.SCRIPT);
        assertThat(technology.applicableTo(pageResponse)).isEqualTo(expected);
    }

    @Test
    public void stringConstructorTest() {
        Technology technology = new Technology("DERAK.CLOUD", TECHNOLOGY_STRING);
        assertThat(technology.getName()).isEqualTo("DERAK.CLOUD");
        assertThat(technology.getDescription()).isEqualTo("DPD is an international parcel delivery service for sorter compatible parcels.");
        assertThat(technology.getWebsite()).isEqualTo("https://derak.cloud");
        assertThat(technology.getIconName()).isEqualTo("DerakCloud.png");
        assertThat(technology.getHeaderTemplates("Derak-Umbrage").get(0).toString()).isEmpty();
        assertThat(technology.getHeaderTemplates("Server").get(0).toString()).isEqualTo("^DERAK\\.CLOUD$");
        assertThat(technology.getCookieTemplates().get("trbo_session").get(0).toString()).isEqualTo("^(?:[\\d]+)$");
        assertThat(technology.getCookieTemplates().get("django_language").get(0).toString()).isEmpty();
    }

    @Test
    public void metaPatternTest() {
        Technology technology = new Technology("Joomla", JOOMLA_TECH_STRING);
        PageResponse pageResponse = new PageResponse(200, null, JOOMLA_META_CONTENT);
        TechnologyMatch expected = new TechnologyMatch(technology, TechnologyMatch.META, 0L);
        assertThat(technology.applicableTo(pageResponse)).isEqualTo(expected);
    }

    @Test
    public void metaEmptyPatternTest() {
        Technology technology = new Technology("JQuery pjax", META_KEY_TECH);
        PageResponse pageResponse = new PageResponse(200, null, META_KEY_CONTENT);
        TechnologyMatch expected = new TechnologyMatch(technology, TechnologyMatch.META, 0L);
        assertThat(technology.applicableTo(pageResponse)).isEqualTo(expected);
    }

    @Test
    public void cpeParseTest() {
        Technology technology = new Technology("Joomla", JOOMLA_TECH_STRING);
        assertThat(technology.getCPE()).isEqualTo("cpe:/a:joomla:joomla");
    }

    @Test
    public void categoriesTest() {
        Categories categories = new Categories(Arrays.asList(
                new Category(41, "Payment processors", 8),
                new Category(91, "Buy now pay later", 9),
                new Category(22, "TEST CATEGORY 1", 9),
                new Category(33, "TEST CATEGORY 2", 9)
        ));
        Technology technology = new Technology("Pace", CATS_TECH, categories);
        assertThat(technology.getCategories()).containsExactlyInAnyOrder(
                new Category(41, "Payment processors", 8),
                new Category(91, "Buy now pay later", 9)
        );
    }

    @Test
    public void pricingTest() {
        Technology technology = new Technology("Jumio", PRICING_SAAS_TECH);
        assertThat(technology.getPricing()).containsExactlyElementsOf(Arrays.asList("payg", "mid", "recurring"));
    }

    @Test
    public void saasTest() {
        Technology technology = new Technology("Jumio", PRICING_SAAS_TECH);
        assertThat(technology.isSaas()).isTrue();
    }

    @Test
    public void shouldTechnologyHasTwoMetaGenerators() {
        Technology technology = new Technology("Abicart", META_GENERATOR_LIST_TECH);
        List<Pattern> generatorTemplates = technology.getMetaTemplates("generator");
        List<String> templateNames = generatorTemplates.stream().map(Pattern::toString).collect(Collectors.toList());
        assertThat(templateNames).containsExactlyInAnyOrder("Abicart", "Textalk Webshop");
    }
}