package interpreter;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * @author susiechoi
 * Used for searching a File/ResourceBundle for a value matching a specific key (or vice versa). 
 * Use by initializing with a ResourceBundle or fileName to the constructor, and 
 * calling findMatchingKey, containsKey, or findMatchingVal, according to the needs of the calling program. 
 * 
 */
class RegexMatcher {

	public static final String DEFAULT_SYNTAX_FILENAME = "interpreter/Syntax";
	public static final String DEFAULT_LANGUAGE_FILENAME = "interpreter/English";
	public static final String DEFAULT_NUMARGS_FILENAME = "interpreter/NumArgsFoCommands";
	private String myFileName; 
	private ResourceBundle myResources; 
	private List<Entry<String, Pattern>> mySymbols;
	private ExceptionFactory myExceptionFactory; 
//	private Exception myException; 
	
	protected RegexMatcher(ResourceBundle resourceBundle) {
		myResources = resourceBundle; 
		mySymbols = new ArrayList<Entry<String, Pattern>>();
		myExceptionFactory = new ExceptionFactory(); 
		populateWithSymbols(mySymbols, myResources);
	}
	
	protected RegexMatcher(String fileName) {
		myFileName = fileName;
		//System.out.println("file: " + fileName);
		myResources = ResourceBundle.getBundle(fileName);
		//System.out.println("made regex well");
		mySymbols = new ArrayList<Entry<String, Pattern>>();
		myExceptionFactory = new ExceptionFactory();
		populateWithSymbols(mySymbols, myResources);
	}
	
	private void populateWithSymbols(List<Entry<String, Pattern>> listToAddTo, ResourceBundle resourcesToAdd) {
        Enumeration<String> iter = resourcesToAdd.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String regex = resourcesToAdd.getString(key);
            listToAddTo.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }
	
	protected String findMatchingKey(String text) throws BadFormatException, UnidentifiedCommandException, MissingInformationException {
		for (Entry<String, Pattern> e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
            else if(text.equals(" ")||text.equals("")) {
            		return "";
            }
        }
		myExceptionFactory.getException(myFileName, text);
		return ""; 
	}
	
	protected boolean containsKey(String text) {
		for (Entry<String, Pattern> e : mySymbols) {
            if (text.equals(e.getKey())) {
            	return true;
            }
        }
		return false; 
	}
	
	protected String findMatchingVal(String text) throws BadFormatException, UnidentifiedCommandException, MissingInformationException {
		String val = ""; 
		try {
			val = myResources.getString(text);
//			System.out.println(val);
		}
		catch (MissingResourceException e) {
			myExceptionFactory.getException(myFileName, text);
		}
		return val; 
	}
	
	private boolean match(String text, Pattern regex) {
        return regex.matcher(text).matches();
    }
	
	
	private class ExceptionFactory{
		
		protected ExceptionFactory() {
		}
		
		protected void getException(String propertiesFile, String issue) throws BadFormatException, UnidentifiedCommandException, MissingInformationException {
			if (propertiesFile.equals(DEFAULT_SYNTAX_FILENAME)) {
				throw new BadFormatException(issue);
			}
			else if (propertiesFile.equals(DEFAULT_LANGUAGE_FILENAME)) {
				throw new UnidentifiedCommandException(issue);
			}
			else if (propertiesFile.equals(DEFAULT_NUMARGS_FILENAME)) {
				throw new MissingInformationException(issue);
			}
		}
	}
	
}
