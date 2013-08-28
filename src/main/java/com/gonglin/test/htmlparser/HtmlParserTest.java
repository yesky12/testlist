package com.gonglin.test.htmlparser;

import java.net.URL;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.beans.LinkBean;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.HeadTag;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.InputTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.OptionTag;
import org.htmlparser.tags.SelectTag;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;
import org.htmlparser.visitors.NodeVisitor;
import org.htmlparser.visitors.ObjectFindingVisitor;

public class HtmlParserTest extends TestCase {
	private static final Logger logger = Logger.getLogger(HtmlParserTest.class);

	public HtmlParserTest(String name) {
		super(name);
	}

	/*
	 * 测试ObjectFindVisitor的用法
	 */
	public void testImageVisitor() {
		try {
			ImageTag imgLink;
			ObjectFindingVisitor visitor = new ObjectFindingVisitor(
					ImageTag.class);
			Parser parser = new Parser();
			parser.setURL("http://profile.china.alibaba.com/user/dengminhui12.html");
			parser.setEncoding(parser.getEncoding());
			parser.visitAllNodesWith(visitor);
			Node[] nodes = visitor.getTags();
			logger.fatal("result of testImageVisitor : size = " + nodes.length);
			for (int i = 0; i < nodes.length; i++) {
				imgLink = (ImageTag) nodes[i];
				logger.fatal("testImageVisitor() ImageURL = "
						+ imgLink.getImageURL());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 测试TagNameFilter用法
	 */
	public void testNodeFilter() {
		try {
			NodeFilter filter = new TagNameFilter("IMG");
			Parser parser = new Parser();
			parser.setURL("http://profile.china.alibaba.com/user/dengminhui12.html");
			parser.setEncoding(parser.getEncoding());
			NodeList list = parser.extractAllNodesThatMatch(filter);
			logger.fatal("result of testNodeFilter : size = " + list.size());
			for (int i = 0; i < list.size(); i++) {
				logger.fatal("testNodeFilter() " + list.elementAt(i).toHtml());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * 测试NodeClassFilter用法
	 */
	public void testLinkTag() {
		try {

			NodeFilter filter = new NodeClassFilter(LinkTag.class);
			Parser parser = new Parser();
			parser.setURL("http://profile.china.alibaba.com/user/dengminhui12.html");
			parser.setEncoding(parser.getEncoding());
			NodeList list = parser.extractAllNodesThatMatch(filter);
			logger.fatal("result of testLinkTag : size = " + list.size());
			for (int i = 0; i < list.size(); i++) {
				LinkTag node = (LinkTag) list.elementAt(i);
				logger.fatal("testLinkTag() Link is :" + node.extractLink());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * 测试<link href=" text=’text/css’ rel=’stylesheet’ />用法
	 */
	public void testLinkCSS() {
		try {

			Parser parser = new Parser();
			parser.setInputHTML("<head><title>Link Test</title>"
					+ "<link href=’/test01/css.css’ text=’text/css’ rel=’stylesheet’ />"
					+ "<link href=’/test02/css.css’ text=’text/css’ rel=’stylesheet’ />"
					+ "</head>" + "<body>");
			parser.setEncoding(parser.getEncoding());
//			NodeList nodeList = null;

			for (NodeIterator e = parser.elements(); e.hasMoreNodes();) {
				Node node = e.nextNode();
				logger.fatal("testLinkCSS()" + node.getText() + node.getClass());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 测试OrFilter的用法
	 */
	public void testOrFilter() {
		NodeFilter inputFilter = new NodeClassFilter(InputTag.class);
		NodeFilter selectFilter = new NodeClassFilter(SelectTag.class);
//		Parser myParser;
		NodeList nodeList = null;

		try {
			Parser parser = new Parser();
			parser.setInputHTML("<head><title>OrFilter Test</title>"
					+ "<link href=’/test01/css.css’ text=’text/css’ rel=’stylesheet’ />"
					+ "<link href=’/test02/css.css’ text=’text/css’ rel=’stylesheet’ />"
					+ "</head>"
					+ "<body>"
					+ "<input type=’text’ value=’text1′ name=’text1′/>"
					+ "<input type=’text’ value=’text2′ name=’text2′/>"
					+ "<select><option id=’1′>1</option><option id=’2′>2</option><option id=’3′></option></select>"
					+ "<a href=’http://www.yeeach.com’>yeeach.com</a>"
					+ "</body>");

			parser.setEncoding(parser.getEncoding());
			OrFilter lastFilter = new OrFilter();
			lastFilter.setPredicates(new NodeFilter[] { selectFilter,
					inputFilter });
			nodeList = parser.parse(lastFilter);
			for (int i = 0; i <= nodeList.size(); i++) {
				if (nodeList.elementAt(i) instanceof InputTag) {
					InputTag tag = (InputTag) nodeList.elementAt(i);
					logger.fatal("OrFilter tag name is :" + tag.getTagName()
							+ " ,tag value is:" + tag.getAttribute("value"));
				}
				if (nodeList.elementAt(i) instanceof SelectTag) {
					SelectTag tag = (SelectTag) nodeList.elementAt(i);
					NodeList list = tag.getChildren();

					for (int j = 0; j < list.size(); j++) {
						OptionTag option = (OptionTag) list.elementAt(j);
						logger.fatal("OrFilter Option" + option.getOptionText());
					}

				}
			}

		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 测试对<table><tr><td></td></tr></table>的解析
	 */
	public void testTable() {
		Parser myParser;
		NodeList nodeList = null;
		myParser = Parser.createParser("<body> " + "<table id=’table1′ >"
				+ "<tr><td>1-11</td><td>1-12</td><td>1-13</td>"
				+ "<tr><td>1-21</td><td>1-22</td><td>1-23</td>"
				+ "<tr><td>1-31</td><td>1-32</td><td>1-33</td></table>"
				+ "<table id=’table2′ >"
				+ "<tr><td>2-11</td><td>2-12</td><td>2-13</td>"
				+ "<tr><td>2-21</td><td>2-22</td><td>2-23</td>"
				+ "<tr><td>2-31</td><td>2-32</td><td>2-33</td></table>"
				+ "</body>", "GBK");
		NodeFilter tableFilter = new NodeClassFilter(TableTag.class);
		OrFilter lastFilter = new OrFilter();
		lastFilter.setPredicates(new NodeFilter[] { tableFilter });
		try {
			nodeList = myParser.parse(lastFilter);
			for (int i = 0; i <= nodeList.size(); i++) {
				if (nodeList.elementAt(i) instanceof TableTag) {
					TableTag tag = (TableTag) nodeList.elementAt(i);
					TableRow[] rows = tag.getRows();

					for (int j = 0; j < rows.length; j++) {
						TableRow tr = (TableRow) rows[j];
						TableColumn[] td = tr.getColumns();
						for (int k = 0; k < td.length; k++) {
							logger.fatal("<td>" + td[k].toPlainTextString());
						}

					}

				}
			}

		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 测试NodeVisitor的用法，遍历所有节点
	 */
	public void testVisitorAll() {
		try {
			Parser parser = new Parser();
			parser.setURL("http://profile.china.alibaba.com/user/dengminhui12.html");
			parser.setEncoding(parser.getEncoding());
			NodeVisitor visitor = new NodeVisitor() {
				public void visitTag(Tag tag) {
					logger.fatal("testVisitorAll()  Tag name is :"
							+ tag.getTagName() + " \n Class is :"
							+ tag.getClass());
				}

			};

			parser.visitAllNodesWith(visitor);
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 测试对指定Tag的NodeVisitor的用法
	 */
	public void testTagVisitor() {
		try {

			Parser parser = new Parser(
					"<head><title>dddd</title>"
							+ "<link href=’/test01/css.css’ text=’text/css’ rel=’stylesheet’ />"
							+ "<link href=’/test02/css.css’ text=’text/css’ rel=’stylesheet’ />"
							+ "</head>"
							+ "<body>"
							+ "<a id='hah' href=’http://www.yeeach.com’>yeeach.com</a>"
							+ "</body>");
			NodeVisitor visitor = new NodeVisitor() {
				public void visitTag(Tag tag) {
					if (tag instanceof HeadTag) {
						logger.fatal("visitTag() HeadTag : Tag name is :"
								+ tag.getTagName() + " \n Class is :"
								+ tag.getClass() + "\n Text is :"
								+ tag.getText());
					} else if (tag instanceof TitleTag) {
						logger.fatal("visitTag() TitleTag : Tag name is :"
								+ tag.getTagName() + " \n Class is :"
								+ tag.getClass() + "\n Text is :"
								+ tag.getText());

					} else if (tag instanceof LinkTag) {
						logger.fatal("visitTag() LinkTag : Tag name is :"
								+ tag.getTagName() + " \n Class is :"
								+ tag.getClass() + "\n Text is :"
								+ tag.getText() + " \n getAttribute is :"
								+ tag.getAttribute("href"));
					} else {
						logger.fatal("visitTag() : Tag name is :"
								+ tag.getTagName() + " \n Class is :"
								+ tag.getClass() + "\n Text is :"
								+ tag.getText());
					}

				}

			};

			parser.visitAllNodesWith(visitor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 测试HtmlPage的用法
	 */
	public void testHtmlPage() {
		String inputHTML = "<html>" + "<head>"
				+ "<title>Welcome to the HTMLParser website</title>"
				+ "</head>" + "<body>" + "Welcome to HTMLParser"
				+ "<table id=’table1′ >"
				+ "<tr><td>1-11</td><td>1-12</td><td>1-13</td>"
				+ "<tr><td>1-21</td><td>1-22</td><td>1-23</td>"
				+ "<tr><td>1-31</td><td>1-32</td><td>1-33</td></table>"
				+ "<table id=’table2′ >"
				+ "<tr><td>2-11</td><td>2-12</td><td>2-13</td>"
				+ "<tr><td>2-21</td><td>2-22</td><td>2-23</td>"
				+ "<tr><td>2-31</td><td>2-32</td><td>2-33</td></table>"
				+ "</body>" + "</html>";
		Parser parser = new Parser();
		try {
			parser.setInputHTML(inputHTML);
			parser.setEncoding(parser.getURL());
			HtmlPage page = new HtmlPage(parser);
			parser.visitAllNodesWith(page);
			logger.fatal("testHtmlPage -title is :" + page.getTitle());
			NodeList list = page.getBody();

			for (NodeIterator iterator = list.elements(); iterator
					.hasMoreNodes();) {
				Node node = iterator.nextNode();
				logger.fatal("testHtmlPage -node  is :" + node.toHtml());
			}

		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 测试LinkBean的用法
	 */
	public void testLinkBean() {
//		Parser parser = new Parser();

		LinkBean linkBean = new LinkBean();
		linkBean.setURL("http://profile.china.alibaba.com/user/dengminhui12.html");
		URL[] urls = linkBean.getLinks();

		for (int i = 0; i < urls.length; i++) {
			URL url = urls[i];
			logger.fatal("testLinkBean() -url  is :" + url);
		}

	}
}
