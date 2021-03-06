import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpExample {

	public static void main(String[] args) {
		//let's introduce a string to match regular expressions against
		String sequence = " 1 agtagttcgc ctgtgtgagc tgacaaactt agtagtgttt gtgaggatta acaacaatta\n" +
				          "61 acacagtgcg agctgtttct tagcacgaag atctcgatgt ctaagaaacc aggagggccc\n" +
				          "121 ggcaagagcc gggctgtcaa tatgctaaaa cgcggaatgc cccgcgtgtt gtccttgatt\n" +
				          "181 ggactgaaga gggctatgtt gagcctgatc gacggcaagg ggccaatacg atttgtgttg\n" ;
		
		System.out.println("The input string is: \n" + sequence);
		
		//TASK 1 - locate the line numbers in the string - we want to get rid of them ultimately
		
		//first we use the Pattern class to compile a regular expression into a Pattern object
		//note: we use a static method call on the Pattern class to do this
		//this pattern will be to locate line numbers
		Pattern lineNumberPattern = Pattern.compile("\\d+");
		//instantiate the Matcher which will match the Pattern against the sequence
		Matcher lineNumberMatcher = lineNumberPattern.matcher(sequence);
		//write a loop to visit each match generated by the Matcher
		while(lineNumberMatcher.find()){
			//print it to show the user
			System.out.println("found: " + lineNumberMatcher.group() + " at position: "+ lineNumberMatcher.start() + " ending at position " + lineNumberMatcher.end());
		}
		//next let's replace all the line numbers with empty strings
		//easy way would be to just use the Matcher.replaceAll() method
		//this will replace every previous match with whatever is inside the string argument
		String modified = lineNumberMatcher.replaceAll("");
		System.out.println("The input string stripped of line numbers: \n" + modified);
		//now let's get rid of all the newlines
		//make a new pattern do accomplish this
		Pattern newLinePattern = Pattern.compile("\n");
		Matcher newLineMatcher = newLinePattern.matcher(modified);
		modified = newLineMatcher.replaceAll("");
		System.out.println("The input string stripped of line numbers and newlines: \n" + modified);
		//last step would be to eliminate whitespace - this I will leave to you as an exercise
		
		//instead, let's demonstrate tokenizing a string using the split method
		Pattern whitespace = Pattern.compile(" ");
		String [] tokens = whitespace.split(modified);
		System.out.println("Tokenized string: \n");
		//let's use a for:each loop to show the tokenized strings
		//I've been avoiding for:each because I think the syntax is kind of gross
		//you can decide
		//read this as: "for each token in tokens"
		for(String token: tokens){
			System.out.println(token);
		}
		//search for a substring within our data
		System.out.println("demonstrating using matcher to find a substring: \n");
		Pattern find = Pattern.compile("aaa.c");
		Matcher matcher = find.matcher(modified);
		while(matcher.find()){
			//print it to show the user
			System.out.println("found: " + matcher.group() + " at position: "+ matcher.start() + " ending at position " + matcher.end());
		}
		
		

	}

}
