package de.secdevops;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

public class InputSanitizer {
	public static String sanitize(String html){
		PolicyFactory policyFactory = new HtmlPolicyBuilder().allowElements(

	          "a", "label", "h1", "h2", "h3", "h4", "h5", "h6",
	          "p", "i", "b", "u", "strong", "em", "small", "big", "pre", "code",
	          "cite", "samp", "sub", "sup", "strike", "center", "blockquote",
	          "hr", "br", "col", "font", "span", "div", "img",
	          "ul", "ol", "li", "dd", "dt", "dl", "tbody", "thead", "tfoot",
	          "table", "td", "th", "tr", "colgroup", "fieldset", "legend"
	      ).toFactory();
		String sanitizedHtml = policyFactory.sanitize(html);
		return sanitizedHtml;
	}
}
