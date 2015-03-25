/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mobi.language;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * @author Ramon Mota
 */
public class Lexer {
	
    public static final int INVALID = -1;
    public static final int NO_TOKENS = 0;
    
    public static final int CREATE_HISTORY = 1;
    public static final int CREATE_RELATION = 2;
    public static final int ADDINSTACE_RELATION  = 3;
    public static final int TYPE = 4;
    public static final int HISTORY = 5;
    public static final int ClASSA = 6;
    public static final int ClASSB = 7;
    public static final int INHERITANCE  = 8;
    public static final int EQUIVALENCE = 9;
    public static final int COMPOSITION = 10;
    public static final int SYMMETRIC = 11;
    
    public static final int FINAL_RELATION = 12;
    public static final int EOL = 13;
    public static final int EOF = 14;
    public static String tokenCurrent = "";
    public int token = 0;

    private StreamTokenizer input;

    public Lexer(InputStream in) {
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        this.input = new StreamTokenizer(r);
        this.input.resetSyntax();
        this.input.eolIsSignificant(true);
       
        this.input.wordChars('a', 'z');
        this.input.wordChars('A', 'Z');
        this.input.wordChars('_', '_');
        this.input.whitespaceChars('\u0000', '\u0020');
        this.input.ordinaryChar('-');
        this.input.ordinaryChar(';');
        this.input.ordinaryChar('(');
        this.input.ordinaryChar(')'); 
        this.input.ordinaryChar(',');
        this.input.ordinaryChar('{');
        this.input.ordinaryChar('}');
        this.input.ordinaryChar('"');
    }

    public int nextToken() throws Exception {
        int token = 0;
        try {
             switch(this.input.nextToken()) {
                case StreamTokenizer.TT_EOF:
                    token = EOF;
                    tokenCurrent = "fim de arquivo";
                    break;
                case StreamTokenizer.TT_EOL:
                	tokenCurrent = "fim de linha";
                    token = EOL;
                    break;
                case StreamTokenizer.TT_WORD:
                	
                	if(this.input.sval.equalsIgnoreCase("CREATE_HISTORY")) {
                		tokenCurrent = this.input.sval;
                		token = CREATE_HISTORY;
                	}
                    if(this.input.sval.equalsIgnoreCase("CREATE_RELATION")) {
                    	tokenCurrent = this.input.sval;
                        token = CREATE_RELATION;
                     } 
                    if(this.input.sval.equalsIgnoreCase("ADDINSTACE_RELATION")) {
                    	tokenCurrent = this.input.sval;
                        token = ADDINSTACE_RELATION;
                     }
                    if(this.input.sval.equalsIgnoreCase("TYPE")) {
                    	tokenCurrent = this.input.sval;
                        token = TYPE;
                     }
                    if(this.input.sval.equalsIgnoreCase("HISTORY")) {
                    	tokenCurrent = this.input.sval;
                        token = HISTORY;
                     }
                    if(this.input.sval.equalsIgnoreCase("ClASSA")) {
                    	tokenCurrent = this.input.sval;
                        token = ClASSA;
                     }
                    if(this.input.sval.equalsIgnoreCase("ClASSB")) {
                    	tokenCurrent = input.sval;
                        token = ClASSB;
                     }
                    if(input.sval.equalsIgnoreCase("INHERITANCE")) {
                    	tokenCurrent = this.input.sval;
                        token = INHERITANCE;
                     }
                    if(this.input.sval.equalsIgnoreCase("EQUIVALENCE")) {
                    	tokenCurrent = this.input.sval;
                        token = EQUIVALENCE;
                     }
                    
                    if(this.input.sval.equalsIgnoreCase("COMPOSITION")) {
                    	tokenCurrent = this.input.sval;
                        token = COMPOSITION;
                     }
                    
                    if(this.input.sval.equalsIgnoreCase("SYMMETRIC")) {
                    	tokenCurrent = this.input.sval;
                        token = SYMMETRIC;
                     }
                    
                    tokenCurrent = this.input.sval;
                      
                    break;
                case '{':
                	tokenCurrent = "{";	
                    token = NO_TOKENS;
                    break;
                case '}':
                	tokenCurrent = "}";
                    token = NO_TOKENS;
                    break;
                case ';':
                	tokenCurrent = ";";
                    token = FINAL_RELATION;
                    break;
                case '(':
                	tokenCurrent = "(";
                    token = NO_TOKENS;
                    break;
                case ',':
                	tokenCurrent = ",";
                    token = NO_TOKENS;
                    break;
                case ')':
                	tokenCurrent = ")";
                    token = NO_TOKENS;
                    break;
                case '-':
                	tokenCurrent = "-";
                    token = NO_TOKENS;
                    break;
                case '"':
                	tokenCurrent = "\"";
                    token = NO_TOKENS;
                    break;     
                case StreamTokenizer.TT_NUMBER:
                    System.err.println("entrou no TT_NUMBER");
                    break;
                default:
                    token = INVALID;
                    throw new Exception("ERRO LEXICO: Cadeia de caracteres invalida.");
            }
        } catch (IOException e) {
            token = EOF;
        }
        return token;
    }

}
