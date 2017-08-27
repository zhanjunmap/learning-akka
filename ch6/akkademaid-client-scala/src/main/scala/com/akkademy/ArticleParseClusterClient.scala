package com.akkademy

import akka.actor.{ActorPath, ActorSystem}
import akka.cluster.client.{ClusterClient, ClusterClientSettings}
import akka.pattern.Patterns
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration.Duration


object ArticleParseClusterClient {

  def main(args: Array[String]) {
    val timeout = new Timeout(Duration.create(5, "seconds"))
    val system = ActorSystem.create("clientSystem")

    val initialContacts: Set[ActorPath] = Set(
      ActorPath.fromString("akka.tcp://Akkademy@127.0.0.1:2552/user/receptionist"),
      ActorPath.fromString("akka.tcp://Akkademy@127.0.0.1:2551/user/receptionist")
    )

    val receptionist = system.actorOf(ClusterClient.props(ClusterClientSettings(system).withInitialContacts(initialContacts)), "client")
    val msg = ClusterClient.Send("/user/workers", articleToParse, false)

    val f = Patterns.ask(receptionist, msg, timeout)
    val result = Await.result(f, timeout.duration).asInstanceOf[String]
    println("result: " + result)
  }

  val articleToParse = "\n<!DOCTYPE html>\n<html lang=\"en\" dir=\"ltr\" class=\"client-nojs\">\n<head>\n<meta charset=\"UTF-8\" />\n<title>Small article monitor - Wikipedia, the free encyclopedia</title>\n<meta name=\"generator\" content=\"MediaWiki 1.26wmf10\" />\n<link rel=\"alternate\" href=\"android-app://org.wikipedia/http/en.m.wikipedia.org/wiki/Small_article_monitor\" />\n<link rel=\"alternate\" type=\"application/x-wiki\" title=\"Edit this page\" href=\"/w/index.php?title=Small_article_monitor&amp;action=edit\" />\n<link rel=\"edit\" title=\"Edit this page\" href=\"/w/index.php?title=Small_article_monitor&amp;action=edit\" />\n<link rel=\"apple-touch-icon\" href=\"/static/apple-touch/wikipedia.png\" />\n<link rel=\"shortcut icon\" href=\"/static/favicon/wikipedia.ico\" />\n<link rel=\"search\" type=\"application/opensearchdescription+xml\" href=\"/w/opensearch_desc.php\" title=\"Wikipedia (en)\" />\n<link rel=\"EditURI\" type=\"application/rsd+xml\" href=\"//en.wikipedia.org/w/api.php?action=rsd\" />\n<link rel=\"alternate\" hreflang=\"x-default\" href=\"/wiki/Small_article_monitor\" />\n<link rel=\"copyright\" href=\"//creativecommons.org/licenses/by-sa/3.0/\" />\n<link rel=\"alternate\" type=\"application/atom+xml\" title=\"Wikipedia Atom feed\" href=\"/w/index.php?title=Special:RecentChanges&amp;feed=atom\" />\n<link rel=\"canonical\" href=\"https://en.wikipedia.org/wiki/Small_article_monitor\" />\n<link rel=\"stylesheet\" href=\"//en.wikipedia.org/w/load.php?debug=false&amp;lang=en&amp;modules=ext.uls.nojs%7Cext.visualEditor.viewPageTarget.noscript%7Cext.wikihiero%7Cmediawiki.legacy.commonPrint%2Cshared%7Cmediawiki.sectionAnchor%7Cmediawiki.skinning.interface%7Cmediawiki.ui.button%7Cskins.vector.styles%7Cwikibase.client.init&amp;only=styles&amp;skin=vector&amp;*\" />\n<meta name=\"ResourceLoaderDynamicStyles\" content=\"\" />\n<link rel=\"stylesheet\" href=\"//en.wikipedia.org/w/load.php?debug=false&amp;lang=en&amp;modules=site&amp;only=styles&amp;skin=vector&amp;*\" />\n<style>a:lang(ar),a:lang(kk-arab),a:lang(mzn),a:lang(ps),a:lang(ur){text-decoration:none}\n/* cache key: enwiki:resourceloader:filter:minify-css:7:3904d24a08aa08f6a68dc338f9be277e */</style>\n<script src=\"//en.wikipedia.org/w/load.php?debug=false&amp;lang=en&amp;modules=startup&amp;only=scripts&amp;skin=vector&amp;*\"></script>\n<script>if(window.mw){\nmw.config.set({\"wgCanonicalNamespace\":\"\",\"wgCanonicalSpecialPageName\":false,\"wgNamespaceNumber\":0,\"wgPageName\":\"Small_article_monitor\",\"wgTitle\":\"Small article monitor\",\"wgCurRevisionId\":291626741,\"wgRevisionId\":291626741,\"wgArticleId\":3166401,\"wgIsArticle\":true,\"wgIsRedirect\":false,\"wgAction\":\"view\",\"wgUserName\":null,\"wgUserGroups\":[\"*\"],\"wgCategories\":[\"Particle detectors\",\"All stub articles\",\"Technology stubs\"],\"wgBreakFrames\":false,\"wgPageContentLanguage\":\"en\",\"wgPageContentModel\":\"wikitext\",\"wgSeparatorTransformTable\":[\"\",\"\"],\"wgDigitTransformTable\":[\"\",\"\"],\"wgDefaultDateFormat\":\"dmy\",\"wgMonthNames\":[\"\",\"January\",\"February\",\"March\",\"April\",\"May\",\"June\",\"July\",\"August\",\"September\",\"October\",\"November\",\"December\"],\"wgMonthNamesShort\":[\"\",\"Jan\",\"Feb\",\"Mar\",\"Apr\",\"May\",\"Jun\",\"Jul\",\"Aug\",\"Sep\",\"Oct\",\"Nov\",\"Dec\"],\"wgRelevantPageName\":\"Small_article_monitor\",\"wgRelevantArticleId\":3166401,\"wgIsProbablyEditable\":true,\"wgRestrictionEdit\":[],\"wgRestrictionMove\":[],\"wgMediaViewerOnClick\":true,\"wgMediaViewerEnabledByDefault\":true,\"wgVisualEditor\":{\"pageLanguageCode\":\"en\",\"pageLanguageDir\":\"ltr\",\"usePageImages\":true,\"usePageDescriptions\":true},\"wikilove-recipient\":\"\",\"wikilove-anon\":0,\"wgPoweredByHHVM\":true,\"wgULSAcceptLanguageList\":[\"en-us\",\"en\"],\"wgULSCurrentAutonym\":\"English\",\"wgWikiEditorEnabledModules\":{\"toolbar\":true,\"dialogs\":true,\"hidesig\":true,\"preview\":false,\"publish\":false},\"wgBetaFeaturesFeatures\":[],\"wgGatherShouldShowTutorial\":true,\"wgFlaggedRevsParams\":{\"tags\":{\"status\":{\"levels\":1,\"quality\":2,\"pristine\":3}}},\"wgStableRevisionId\":null,\"wgCategoryTreePageCategoryOptions\":\"{\\\"mode\\\":0,\\\"hideprefix\\\":20,\\\"showcount\\\":true,\\\"namespaces\\\":false}\",\"wgNoticeProject\":\"wikipedia\",\"wgWikibaseItemId\":\"Q7543001\"});\n}</script><script>if(window.mw){\nmw.loader.implement(\"user.options\",function($,jQuery){mw.user.options.set({\"variant\":\"en\"});});\n/* cache key: enwiki:resourceloader:filter:minify-js:7:b2706269305541eba923c165462b22c4 */\n}</script>\n<script>if(window.mw){\nmw.loader.implement(\"user.tokens\",function($,jQuery){mw.user.tokens.set({\"editToken\":\"+\\\\\",\"patrolToken\":\"+\\\\\",\"watchToken\":\"+\\\\\"});});\n}</script>\n<script>if(window.mw){\nmw.loader.load([\"mediawiki.page.startup\",\"mediawiki.legacy.wikibits\",\"mediawiki.legacy.ajax\",\"ext.centralauth.centralautologin\",\"mmv.head\",\"ext.imageMetrics.head\",\"ext.visualEditor.viewPageTarget.init\",\"ext.uls.init\",\"ext.uls.interface\",\"ext.centralNotice.bannerController\",\"skins.vector.js\"]);\n}</script>\n<link rel=\"dns-prefetch\" href=\"//meta.wikimedia.org\" />\n<!--[if lt IE 7]><style type=\"text/css\">body{behavior:url(\"/w/static/1.26wmf10/skins/Vector/csshover.min.htc\")}</style><![endif]-->\n</head>\n<body class=\"mediawiki ltr sitedir-ltr ns-0 ns-subject page-Small_article_monitor skin-vector action-view\">\n\t\t<div id=\"mw-page-base\" class=\"noprint\"></div>\n\t\t<div id=\"mw-head-base\" class=\"noprint\"></div>\n\t\t<div id=\"content\" class=\"mw-body\" role=\"main\">\n\t\t\t<a id=\"top\"></a>\n\n\t\t\t\t\t\t\t<div id=\"siteNotice\"><!-- CentralNotice --></div>\n\t\t\t\t\t\t<div class=\"mw-indicators\">\n</div>\n\t\t\t<h1 id=\"firstHeading\" class=\"firstHeading\" lang=\"en\">Small article monitor</h1>\n\t\t\t\t\t\t\t\t\t<div id=\"bodyContent\" class=\"mw-body-content\">\n\t\t\t\t\t\t\t\t\t<div id=\"siteSub\">From Wikipedia, the free encyclopedia</div>\n\t\t\t\t\t\t\t\t<div id=\"contentSub\"></div>\n\t\t\t\t\t\t\t\t\t\t\t\t<div id=\"jump-to-nav\" class=\"mw-jump\">\n\t\t\t\t\tJump to:\t\t\t\t\t<a href=\"#mw-head\">navigation</a>, \t\t\t\t\t<a href=\"#p-search\">search</a>\n\t\t\t\t</div>\n\t\t\t\t<div id=\"mw-content-text\" lang=\"en\" dir=\"ltr\" class=\"mw-content-ltr\"><p>A <b>Small Article Monitor</b> or <b>SAM</b> is a monitoring device designed to screen small items of up to 50 pounds weight for <a href=\"/wiki/Radioactive_contamination\" title=\"Radioactive contamination\">radioactive contamination</a>. It uses six plastic <a href=\"/wiki/Scintillation_detector\" title=\"Scintillation detector\" class=\"mw-redirect\">scintillation detectors</a>, one each on the top, bottom, back, left and right sides of the chamber, plus one in the door. Operation of the instrument is controlled from an integral terminal. The instrument performs a self-test and acquires a new <a href=\"/wiki/Background_count\" title=\"Background count\" class=\"mw-redirect\">Background count</a> each time it is powered up. It also monitors its own operation during normal use and indicates any failures. It runs continuously, updating backgrounds whenever no weight is detected inside the chamber. A new count is initiated every time a door open/door close sequence is detected.</p>\n<p>Because of overall interference a <a href=\"/wiki/Geiger_counter\" title=\"Geiger counter\">Geiger counter</a> would be rendered ineffective within the walls of a nuclear containment building for screening individual articles (i.e., clothing, tools, etc.) for specific contamination. A SAM can measure nuclear contamination of specific articles without interference from its outside environment due to the thickness of its lead casing. SAMs range in weight from 1900lbs to 3500lbs.</p>\n<h2><span class=\"mw-headline\" id=\"References\">References</span><span class=\"mw-editsection\"><span class=\"mw-editsection-bracket\">[</span><a href=\"/w/index.php?title=Small_article_monitor&amp;action=edit&amp;section=1\" title=\"Edit section: References\">edit</a><span class=\"mw-editsection-bracket\">]</span></span></h2>\n<ul>\n<li><a rel=\"nofollow\" class=\"external text\" href=\"http://www.tsasystems.com/library/manuals/bm185_manual.pdf\">TSA Systems BM-185 manual</a></li>\n</ul>\n<p><br /></p>\n<table class=\"metadata plainlinks stub\" role=\"presentation\" style=\"background:transparent\">\n<tr>\n<td><a href=\"/wiki/File:Crystal_Clear_action_run.png\" class=\"image\"><img alt=\"Stub icon\" src=\"//upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Crystal_Clear_action_run.png/30px-Crystal_Clear_action_run.png\" width=\"30\" height=\"30\" srcset=\"//upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Crystal_Clear_action_run.png/45px-Crystal_Clear_action_run.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Crystal_Clear_action_run.png/60px-Crystal_Clear_action_run.png 2x\" data-file-width=\"128\" data-file-height=\"128\" /></a></td>\n<td><i>This technology-related article is a <a href=\"/wiki/Wikipedia:Stub\" title=\"Wikipedia:Stub\">stub</a>. You can help Wikipedia by <a class=\"external text\" href=\"//en.wikipedia.org/w/index.php?title=Small_article_monitor&amp;action=edit\">expanding it</a>.</i>\n<div class=\"plainlinks hlist navbar mini\" style=\"position: absolute; right: 15px; display: none;\">\n<ul>\n<li class=\"nv-view\"><a href=\"/wiki/Template:Tech-stub\" title=\"Template:Tech-stub\"><span title=\"View this template\">v</span></a></li>\n<li class=\"nv-talk\"><a href=\"/wiki/Template_talk:Tech-stub\" title=\"Template talk:Tech-stub\"><span title=\"Discuss this template\">t</span></a></li>\n<li class=\"nv-edit\"><a class=\"external text\" href=\"//en.wikipedia.org/w/index.php?title=Template:Tech-stub&amp;action=edit\"><span title=\"Edit this template\">e</span></a></li>\n</ul>\n</div>\n</td>\n</tr>\n</table>\n\n\n<!-- \nNewPP limit report\nParsed by mw1070\nCPU time usage: 0.064 seconds\nReal time usage: 0.076 seconds\nPreprocessor visited node count: 38/1000000\nPreprocessor generated node count: 0/1500000\nPost‐expand include size: 2580/2097152 bytes\nTemplate argument size: 0/2097152 bytes\nHighest expansion depth: 3/40\nExpensive parser function count: 0/500\nLua time usage: 0.013/10.000 seconds\nLua memory usage: 681 KB/50 MB\n-->\n\n<!-- \nTransclusion expansion time report (%,ms,calls,template)\n100.00%   36.681      1 - Template:Tech-stub\n100.00%   36.681      1 - -total\n 93.35%   34.242      1 - Template:Asbox\n-->\n\n<!-- Saved in parser cache with key enwiki:pcache:idhash:3166401-0!*!0!*!*!4!* and timestamp 20150614231627 and revision id 291626741\n -->\n<noscript><img src=\"//en.wikipedia.org/wiki/Special:CentralAutoLogin/start?type=1x1\" alt=\"\" title=\"\" width=\"1\" height=\"1\" style=\"border: none; position: absolute;\" /></noscript></div>\t\t\t\t\t<div class=\"printfooter\">\n\t\t\t\t\t\tRetrieved from \"<a dir=\"ltr\" href=\"https://en.wikipedia.org/w/index.php?title=Small_article_monitor&amp;oldid=291626741\">https://en.wikipedia.org/w/index.php?title=Small_article_monitor&amp;oldid=291626741</a>\"\t\t\t\t\t</div>\n\t\t\t\t<div id='catlinks' class='catlinks'><div id=\"mw-normal-catlinks\" class=\"mw-normal-catlinks\"><a href=\"/wiki/Help:Category\" title=\"Help:Category\">Categories</a>: <ul><li><a href=\"/wiki/Category:Particle_detectors\" title=\"Category:Particle detectors\">Particle detectors</a></li><li><a href=\"/wiki/Category:Technology_stubs\" title=\"Category:Technology stubs\">Technology stubs</a></li></ul></div><div id=\"mw-hidden-catlinks\" class=\"mw-hidden-catlinks mw-hidden-cats-hidden\">Hidden categories: <ul><li><a href=\"/wiki/Category:All_stub_articles\" title=\"Category:All stub articles\">All stub articles</a></li></ul></div></div>\t\t\t\t<div class=\"visualClear\"></div>\n\t\t\t\t\t\t\t</div>\n\t\t</div>\n\t\t<div id=\"mw-navigation\">\n\t\t\t<h2>Navigation menu</h2>\n\n\t\t\t<div id=\"mw-head\">\n\t\t\t\t\t\t\t\t\t<div id=\"p-personal\" role=\"navigation\" class=\"\" aria-labelledby=\"p-personal-label\">\n\t\t\t\t\t\t<h3 id=\"p-personal-label\">Personal tools</h3>\n\t\t\t\t\t\t<ul>\n\t\t\t\t\t\t\t<li id=\"pt-createaccount\"><a href=\"/w/index.php?title=Special:UserLogin&amp;returnto=Small+article+monitor&amp;type=signup\" title=\"You are encouraged to create an account and log in; however, it is not mandatory\">Create account</a></li><li id=\"pt-login\"><a href=\"/w/index.php?title=Special:UserLogin&amp;returnto=Small+article+monitor\" title=\"You're encouraged to log in; however, it's not mandatory. [o]\" accesskey=\"o\">Log in</a></li>\t\t\t\t\t\t</ul>\n\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t<div id=\"left-navigation\">\n\t\t\t\t\t\t\t\t\t\t<div id=\"p-namespaces\" role=\"navigation\" class=\"vectorTabs\" aria-labelledby=\"p-namespaces-label\">\n\t\t\t\t\t\t<h3 id=\"p-namespaces-label\">Namespaces</h3>\n\t\t\t\t\t\t<ul>\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<li  id=\"ca-nstab-main\" class=\"selected\"><span><a href=\"/wiki/Small_article_monitor\"  title=\"View the content page [c]\" accesskey=\"c\">Article</a></span></li>\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<li  id=\"ca-talk\"><span><a href=\"/wiki/Talk:Small_article_monitor\"  title=\"Discussion about the content page [t]\" accesskey=\"t\" rel=\"discussion\">Talk</a></span></li>\n\t\t\t\t\t\t\t\t\t\t\t\t\t</ul>\n\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t\t<div id=\"p-variants\" role=\"navigation\" class=\"vectorMenu emptyPortlet\" aria-labelledby=\"p-variants-label\">\n\t\t\t\t\t\t\t\t\t\t\t\t<h3 id=\"p-variants-label\">\n\t\t\t\t\t\t\t<span>Variants</span><a href=\"#\"></a>\n\t\t\t\t\t\t</h3>\n\n\t\t\t\t\t\t<div class=\"menu\">\n\t\t\t\t\t\t\t<ul>\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</ul>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t<div id=\"right-navigation\">\n\t\t\t\t\t\t\t\t\t\t<div id=\"p-views\" role=\"navigation\" class=\"vectorTabs\" aria-labelledby=\"p-views-label\">\n\t\t\t\t\t\t<h3 id=\"p-views-label\">Views</h3>\n\t\t\t\t\t\t<ul>\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<li id=\"ca-view\" class=\"selected\"><span><a href=\"/wiki/Small_article_monitor\" >Read</a></span></li>\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<li id=\"ca-edit\"><span><a href=\"/w/index.php?title=Small_article_monitor&amp;action=edit\"  title=\"You can edit this page. Please use the preview button before saving [e]\" accesskey=\"e\">Edit</a></span></li>\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<li id=\"ca-history\" class=\"collapsible\"><span><a href=\"/w/index.php?title=Small_article_monitor&amp;action=history\"  title=\"Past versions of this page [h]\" accesskey=\"h\">View history</a></span></li>\n\t\t\t\t\t\t\t\t\t\t\t\t\t</ul>\n\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t\t<div id=\"p-cactions\" role=\"navigation\" class=\"vectorMenu emptyPortlet\" aria-labelledby=\"p-cactions-label\">\n\t\t\t\t\t\t<h3 id=\"p-cactions-label\"><span>More</span><a href=\"#\"></a></h3>\n\n\t\t\t\t\t\t<div class=\"menu\">\n\t\t\t\t\t\t\t<ul>\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</ul>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t\t<div id=\"p-search\" role=\"search\">\n\t\t\t\t\t\t<h3>\n\t\t\t\t\t\t\t<label for=\"searchInput\">Search</label>\n\t\t\t\t\t\t</h3>\n\n\t\t\t\t\t\t<form action=\"/w/index.php\" id=\"searchform\">\n\t\t\t\t\t\t\t<div id=\"simpleSearch\">\n\t\t\t\t\t\t\t<input type=\"search\" name=\"search\" placeholder=\"Search\" title=\"Search Wikipedia [f]\" accesskey=\"f\" id=\"searchInput\" /><input type=\"hidden\" value=\"Special:Search\" name=\"title\" /><input type=\"submit\" name=\"fulltext\" value=\"Search\" title=\"Search Wikipedia for this text\" id=\"mw-searchButton\" class=\"searchButton mw-fallbackSearchButton\" /><input type=\"submit\" name=\"go\" value=\"Go\" title=\"Go to a page with this exact name if one exists\" id=\"searchButton\" class=\"searchButton\" />\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t</form>\n\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t\t</div>\n\t\t\t</div>\n\t\t\t<div id=\"mw-panel\">\n\t\t\t\t<div id=\"p-logo\" role=\"banner\"><a class=\"mw-wiki-logo\" href=\"/wiki/Main_Page\"  title=\"Visit the main page\"></a></div>\n\t\t\t\t\t\t<div class=\"portal\" role=\"navigation\" id='p-navigation' aria-labelledby='p-navigation-label'>\n\t\t\t<h3 id='p-navigation-label'>Navigation</h3>\n\n\t\t\t<div class=\"body\">\n\t\t\t\t\t\t\t\t\t<ul>\n\t\t\t\t\t\t<li id=\"n-mainpage-description\"><a href=\"/wiki/Main_Page\" title=\"Visit the main page [z]\" accesskey=\"z\">Main page</a></li><li id=\"n-contents\"><a href=\"/wiki/Portal:Contents\" title=\"Guides to browsing Wikipedia\">Contents</a></li><li id=\"n-featuredcontent\"><a href=\"/wiki/Portal:Featured_content\" title=\"Featured content – the best of Wikipedia\">Featured content</a></li><li id=\"n-currentevents\"><a href=\"/wiki/Portal:Current_events\" title=\"Find background information on current events\">Current events</a></li><li id=\"n-randompage\"><a href=\"/wiki/Special:Random\" title=\"Load a random article [x]\" accesskey=\"x\">Random article</a></li><li id=\"n-sitesupport\"><a href=\"https://donate.wikimedia.org/wiki/Special:FundraiserRedirector?utm_source=donate&amp;utm_medium=sidebar&amp;utm_campaign=C13_en.wikipedia.org&amp;uselang=en\" title=\"Support us\">Donate to Wikipedia</a></li><li id=\"n-shoplink\"><a href=\"//shop.wikimedia.org\" title=\"Visit the Wikimedia Shop\">Wikipedia store</a></li>\t\t\t\t\t</ul>\n\t\t\t\t\t\t\t</div>\n\t\t</div>\n\t\t\t<div class=\"portal\" role=\"navigation\" id='p-interaction' aria-labelledby='p-interaction-label'>\n\t\t\t<h3 id='p-interaction-label'>Interaction</h3>\n\n\t\t\t<div class=\"body\">\n\t\t\t\t\t\t\t\t\t<ul>\n\t\t\t\t\t\t<li id=\"n-help\"><a href=\"/wiki/Help:Contents\" title=\"Guidance on how to use and edit Wikipedia\">Help</a></li><li id=\"n-aboutsite\"><a href=\"/wiki/Wikipedia:About\" title=\"Find out about Wikipedia\">About Wikipedia</a></li><li id=\"n-portal\"><a href=\"/wiki/Wikipedia:Community_portal\" title=\"About the project, what you can do, where to find things\">Community portal</a></li><li id=\"n-recentchanges\"><a href=\"/wiki/Special:RecentChanges\" title=\"A list of recent changes in the wiki [r]\" accesskey=\"r\">Recent changes</a></li><li id=\"n-contactpage\"><a href=\"//en.wikipedia.org/wiki/Wikipedia:Contact_us\">Contact page</a></li>\t\t\t\t\t</ul>\n\t\t\t\t\t\t\t</div>\n\t\t</div>\n\t\t\t<div class=\"portal\" role=\"navigation\" id='p-tb' aria-labelledby='p-tb-label'>\n\t\t\t<h3 id='p-tb-label'>Tools</h3>\n\n\t\t\t<div class=\"body\">\n\t\t\t\t\t\t\t\t\t<ul>\n\t\t\t\t\t\t<li id=\"t-whatlinkshere\"><a href=\"/wiki/Special:WhatLinksHere/Small_article_monitor\" title=\"List of all English Wikipedia pages containing links to this page [j]\" accesskey=\"j\">What links here</a></li><li id=\"t-recentchangeslinked\"><a href=\"/wiki/Special:RecentChangesLinked/Small_article_monitor\" title=\"Recent changes in pages linked from this page [k]\" accesskey=\"k\">Related changes</a></li><li id=\"t-upload\"><a href=\"/wiki/Wikipedia:File_Upload_Wizard\" title=\"Upload files [u]\" accesskey=\"u\">Upload file</a></li><li id=\"t-specialpages\"><a href=\"/wiki/Special:SpecialPages\" title=\"A list of all special pages [q]\" accesskey=\"q\">Special pages</a></li><li id=\"t-permalink\"><a href=\"/w/index.php?title=Small_article_monitor&amp;oldid=291626741\" title=\"Permanent link to this revision of the page\">Permanent link</a></li><li id=\"t-info\"><a href=\"/w/index.php?title=Small_article_monitor&amp;action=info\" title=\"More information about this page\">Page information</a></li><li id=\"t-wikibase\"><a href=\"//www.wikidata.org/wiki/Q7543001\" title=\"Link to connected data repository item [g]\" accesskey=\"g\">Wikidata item</a></li><li id=\"t-cite\"><a href=\"/w/index.php?title=Special:CiteThisPage&amp;page=Small_article_monitor&amp;id=291626741\" title=\"Information on how to cite this page\">Cite this page</a></li>\t\t\t\t\t</ul>\n\t\t\t\t\t\t\t</div>\n\t\t</div>\n\t\t\t<div class=\"portal\" role=\"navigation\" id='p-coll-print_export' aria-labelledby='p-coll-print_export-label'>\n\t\t\t<h3 id='p-coll-print_export-label'>Print/export</h3>\n\n\t\t\t<div class=\"body\">\n\t\t\t\t\t\t\t\t\t<ul>\n\t\t\t\t\t\t<li id=\"coll-create_a_book\"><a href=\"/w/index.php?title=Special:Book&amp;bookcmd=book_creator&amp;referer=Small+article+monitor\">Create a book</a></li><li id=\"coll-download-as-rdf2latex\"><a href=\"/w/index.php?title=Special:Book&amp;bookcmd=render_article&amp;arttitle=Small+article+monitor&amp;oldid=291626741&amp;writer=rdf2latex\">Download as PDF</a></li><li id=\"t-print\"><a href=\"/w/index.php?title=Small_article_monitor&amp;printable=yes\" title=\"Printable version of this page [p]\" accesskey=\"p\">Printable version</a></li>\t\t\t\t\t</ul>\n\t\t\t\t\t\t\t</div>\n\t\t</div>\n\t\t\t<div class=\"portal\" role=\"navigation\" id='p-lang' aria-labelledby='p-lang-label'>\n\t\t\t<h3 id='p-lang-label'>Languages</h3>\n\n\t\t\t<div class=\"body\">\n\t\t\t\t\t\t\t\t\t<ul>\n\t\t\t\t\t\t<li class=\"uls-p-lang-dummy\"><a href=\"#\"></a></li>\t\t\t\t\t</ul>\n\t\t\t\t<div class='after-portlet after-portlet-lang'><span class=\"wb-langlinks-edit wb-langlinks-link\"><a href=\"//www.wikidata.org/wiki/Q7543001#sitelinks-wikipedia\" title=\"Edit interlanguage links\" class=\"wbc-editpage\">Edit links</a></span></div>\t\t\t</div>\n\t\t</div>\n\t\t\t\t</div>\n\t\t</div>\n\t\t<div id=\"footer\" role=\"contentinfo\">\n\t\t\t\t\t\t\t<ul id=\"footer-info\">\n\t\t\t\t\t\t\t\t\t\t\t<li id=\"footer-info-lastmod\"> This page was last modified on 22 May 2009, at 15:11.</li>\n\t\t\t\t\t\t\t\t\t\t\t<li id=\"footer-info-copyright\">Text is available under the <a rel=\"license\" href=\"//en.wikipedia.org/wiki/Wikipedia:Text_of_Creative_Commons_Attribution-ShareAlike_3.0_Unported_License\">Creative Commons Attribution-ShareAlike License</a><a rel=\"license\" href=\"//creativecommons.org/licenses/by-sa/3.0/\" style=\"display:none;\"></a>;\nadditional terms may apply.  By using this site, you agree to the <a href=\"//wikimediafoundation.org/wiki/Terms_of_Use\">Terms of Use</a> and <a href=\"//wikimediafoundation.org/wiki/Privacy_policy\">Privacy Policy</a>. Wikipedia® is a registered trademark of the <a href=\"//www.wikimediafoundation.org/\">Wikimedia Foundation, Inc.</a>, a non-profit organization.</li>\n\t\t\t\t\t\t\t\t\t</ul>\n\t\t\t\t\t\t\t<ul id=\"footer-places\">\n\t\t\t\t\t\t\t\t\t\t\t<li id=\"footer-places-privacy\"><a href=\"//wikimediafoundation.org/wiki/Privacy_policy\" title=\"wikimedia:Privacy policy\">Privacy policy</a></li>\n\t\t\t\t\t\t\t\t\t\t\t<li id=\"footer-places-about\"><a href=\"/wiki/Wikipedia:About\" title=\"Wikipedia:About\">About Wikipedia</a></li>\n\t\t\t\t\t\t\t\t\t\t\t<li id=\"footer-places-disclaimer\"><a href=\"/wiki/Wikipedia:General_disclaimer\" title=\"Wikipedia:General disclaimer\">Disclaimers</a></li>\n\t\t\t\t\t\t\t\t\t\t\t<li id=\"footer-places-contact\"><a href=\"//en.wikipedia.org/wiki/Wikipedia:Contact_us\">Contact Wikipedia</a></li>\n\t\t\t\t\t\t\t\t\t\t\t<li id=\"footer-places-developers\"><a href=\"https://www.mediawiki.org/wiki/Special:MyLanguage/How_to_contribute\">Developers</a></li>\n\t\t\t\t\t\t\t\t\t\t\t<li id=\"footer-places-mobileview\"><a href=\"//en.m.wikipedia.org/w/index.php?title=Small_article_monitor&amp;mobileaction=toggle_view_mobile\" class=\"noprint stopMobileRedirectToggle\">Mobile view</a></li>\n\t\t\t\t\t\t\t\t\t</ul>\n\t\t\t\t\t\t\t\t\t\t<ul id=\"footer-icons\" class=\"noprint\">\n\t\t\t\t\t\t\t\t\t\t\t<li id=\"footer-copyrightico\">\n\t\t\t\t\t\t\t<a href=\"//wikimediafoundation.org/\"><img src=\"/static/images/wikimedia-button.png\" srcset=\"/static/images/wikimedia-button-1.5x.png 1.5x, /static/images/wikimedia-button-2x.png 2x\" width=\"88\" height=\"31\" alt=\"Wikimedia Foundation\"/></a>\t\t\t\t\t\t</li>\n\t\t\t\t\t\t\t\t\t\t\t<li id=\"footer-poweredbyico\">\n\t\t\t\t\t\t\t<a href=\"//www.mediawiki.org/\"><img src=\"//en.wikipedia.org/static/1.26wmf10/resources/assets/poweredby_mediawiki_88x31.png\" alt=\"Powered by MediaWiki\" srcset=\"//en.wikipedia.org/static/1.26wmf10/resources/assets/poweredby_mediawiki_132x47.png 1.5x, //en.wikipedia.org/static/1.26wmf10/resources/assets/poweredby_mediawiki_176x62.png 2x\" width=\"88\" height=\"31\" /></a>\t\t\t\t\t\t</li>\n\t\t\t\t\t\t\t\t\t</ul>\n\t\t\t\t\t\t<div style=\"clear:both\"></div>\n\t\t</div>\n\t\t<script>if(window.jQuery)jQuery.ready();</script><script>if(window.mw){\nmw.loader.state({\"ext.globalCssJs.site\":\"ready\",\"ext.globalCssJs.user\":\"ready\",\"site\":\"loading\",\"user\":\"ready\",\"user.groups\":\"ready\"});\n}</script>\n<link rel=\"stylesheet\" href=\"//en.wikipedia.org/w/load.php?debug=false&amp;lang=en&amp;modules=ext.gadget.DRN-wizard%2CReferenceTooltips%2Ccharinsert%2Cfeatured-articles-links%2CrefToolbar%2Cswitcher%2Cteahouse%7Cext.wikimediaBadges&amp;only=styles&amp;skin=vector&amp;*\" />\n<script>if(window.mw){\nmw.loader.load([\"mediawiki.action.view.postEdit\",\"mediawiki.user\",\"mediawiki.hidpi\",\"mediawiki.page.ready\",\"mediawiki.searchSuggest\",\"ext.cirrusSearch.loggingSchema\",\"mmv.bootstrap.autostart\",\"ext.imageMetrics.loader\",\"ext.visualEditor.targetLoader\",\"ext.eventLogging.subscriber\",\"ext.wikimediaEvents\",\"ext.wikimediaEvents.statsd\",\"ext.navigationTiming\",\"schema.UniversalLanguageSelector\",\"ext.uls.eventlogger\",\"ext.uls.interlanguage\",\"ext.gadget.teahouse\",\"ext.gadget.ReferenceTooltips\",\"ext.gadget.DRN-wizard\",\"ext.gadget.charinsert\",\"ext.gadget.refToolbar\",\"ext.gadget.switcher\",\"ext.gadget.featured-articles-links\"],null,true);\n}</script>\n<script>if(window.mw){\ndocument.write(\"\\u003Cscript src=\\\"//en.wikipedia.org/w/load.php?debug=false\\u0026amp;lang=en\\u0026amp;modules=site\\u0026amp;only=scripts\\u0026amp;skin=vector\\u0026amp;*\\\"\\u003E\\u003C/script\\u003E\");\n}</script>\n<script>if(window.mw){\nmw.config.set({\"wgBackendResponseTime\":102,\"wgHostname\":\"mw1093\"});\n}</script>\n\t</body>\n</html>"
}

