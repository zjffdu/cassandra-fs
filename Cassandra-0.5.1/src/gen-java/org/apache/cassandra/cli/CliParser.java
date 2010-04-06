// $ANTLR 3.1.3 Mar 18, 2009 10:09:25 D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g 2010-03-26 22:50:19

package org.apache.cassandra.cli;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class CliParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "NODE_CONNECT", "NODE_DESCRIBE_TABLE", "NODE_EXIT", "NODE_HELP", "NODE_NO_OP", "NODE_SHOW_CLUSTER_NAME", "NODE_SHOW_CONFIG_FILE", "NODE_SHOW_VERSION", "NODE_SHOW_TABLES", "NODE_THRIFT_GET", "NODE_THRIFT_SET", "NODE_THRIFT_COUNT", "NODE_THRIFT_DEL", "NODE_COLUMN_ACCESS", "NODE_ID_LIST", "SEMICOLON", "K_CONNECT", "SLASH", "K_HELP", "K_QUIT", "K_EXIT", "K_GET", "K_SET", "K_COUNT", "K_DEL", "K_SHOW", "K_CLUSTER", "K_NAME", "K_CONFIG", "K_FILE", "K_VERSION", "K_TABLES", "K_DESCRIBE", "K_TABLE", "DOT", "Identifier", "StringLiteral", "IntegerLiteral", "Letter", "Digit", "Alnum", "WS", "COMMENT", "'?'", "'='", "'['", "']'"
    };
    public static final int K_TABLES=35;
    public static final int NODE_EXIT=6;
    public static final int K_EXIT=24;
    public static final int K_GET=25;
    public static final int K_CONNECT=20;
    public static final int K_DEL=28;
    public static final int K_CONFIG=32;
    public static final int EOF=-1;
    public static final int Identifier=39;
    public static final int K_SET=26;
    public static final int K_DESCRIBE=36;
    public static final int NODE_SHOW_VERSION=11;
    public static final int NODE_CONNECT=4;
    public static final int SLASH=21;
    public static final int NODE_SHOW_TABLES=12;
    public static final int K_CLUSTER=30;
    public static final int NODE_DESCRIBE_TABLE=5;
    public static final int K_SHOW=29;
    public static final int K_TABLE=37;
    public static final int COMMENT=46;
    public static final int K_NAME=31;
    public static final int DOT=38;
    public static final int T__50=50;
    public static final int K_QUIT=23;
    public static final int NODE_SHOW_CONFIG_FILE=10;
    public static final int K_COUNT=27;
    public static final int T__47=47;
    public static final int K_VERSION=34;
    public static final int NODE_THRIFT_DEL=16;
    public static final int T__48=48;
    public static final int K_FILE=33;
    public static final int T__49=49;
    public static final int SEMICOLON=19;
    public static final int Digit=43;
    public static final int NODE_THRIFT_GET=13;
    public static final int StringLiteral=40;
    public static final int NODE_THRIFT_SET=14;
    public static final int NODE_NO_OP=8;
    public static final int NODE_HELP=7;
    public static final int NODE_ID_LIST=18;
    public static final int WS=45;
    public static final int NODE_THRIFT_COUNT=15;
    public static final int K_HELP=22;
    public static final int Alnum=44;
    public static final int IntegerLiteral=41;
    public static final int NODE_SHOW_CLUSTER_NAME=9;
    public static final int Letter=42;
    public static final int NODE_COLUMN_ACCESS=17;

    // delegates
    // delegators


        public CliParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public CliParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return CliParser.tokenNames; }
    public String getGrammarFileName() { return "D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g"; }


    public static class root_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "root"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:69:1: root : stmt ( SEMICOLON )? EOF -> stmt ;
    public final CliParser.root_return root() throws RecognitionException {
        CliParser.root_return retval = new CliParser.root_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SEMICOLON2=null;
        Token EOF3=null;
        CliParser.stmt_return stmt1 = null;


        CommonTree SEMICOLON2_tree=null;
        CommonTree EOF3_tree=null;
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_stmt=new RewriteRuleSubtreeStream(adaptor,"rule stmt");
        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:69:5: ( stmt ( SEMICOLON )? EOF -> stmt )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:69:7: stmt ( SEMICOLON )? EOF
            {
            pushFollow(FOLLOW_stmt_in_root208);
            stmt1=stmt();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_stmt.add(stmt1.getTree());
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:69:12: ( SEMICOLON )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==SEMICOLON) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:0:0: SEMICOLON
                    {
                    SEMICOLON2=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_root210); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMICOLON.add(SEMICOLON2);


                    }
                    break;

            }

            EOF3=(Token)match(input,EOF,FOLLOW_EOF_in_root213); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EOF.add(EOF3);



            // AST REWRITE
            // elements: stmt
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 69:27: -> stmt
            {
                adaptor.addChild(root_0, stream_stmt.nextTree());

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "root"

    public static class stmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "stmt"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:71:1: stmt : ( connectStmt | exitStmt | countStmt | describeTable | delStmt | getStmt | helpStmt | setStmt | showStmt | -> ^( NODE_NO_OP ) );
    public final CliParser.stmt_return stmt() throws RecognitionException {
        CliParser.stmt_return retval = new CliParser.stmt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CliParser.connectStmt_return connectStmt4 = null;

        CliParser.exitStmt_return exitStmt5 = null;

        CliParser.countStmt_return countStmt6 = null;

        CliParser.describeTable_return describeTable7 = null;

        CliParser.delStmt_return delStmt8 = null;

        CliParser.getStmt_return getStmt9 = null;

        CliParser.helpStmt_return helpStmt10 = null;

        CliParser.setStmt_return setStmt11 = null;

        CliParser.showStmt_return showStmt12 = null;



        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:72:5: ( connectStmt | exitStmt | countStmt | describeTable | delStmt | getStmt | helpStmt | setStmt | showStmt | -> ^( NODE_NO_OP ) )
            int alt2=10;
            switch ( input.LA(1) ) {
            case K_CONNECT:
                {
                alt2=1;
                }
                break;
            case K_QUIT:
            case K_EXIT:
                {
                alt2=2;
                }
                break;
            case K_COUNT:
                {
                alt2=3;
                }
                break;
            case K_DESCRIBE:
                {
                alt2=4;
                }
                break;
            case K_DEL:
                {
                alt2=5;
                }
                break;
            case K_GET:
                {
                alt2=6;
                }
                break;
            case K_HELP:
            case 47:
                {
                alt2=7;
                }
                break;
            case K_SET:
                {
                alt2=8;
                }
                break;
            case K_SHOW:
                {
                alt2=9;
                }
                break;
            case EOF:
            case SEMICOLON:
                {
                alt2=10;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:72:7: connectStmt
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_connectStmt_in_stmt229);
                    connectStmt4=connectStmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, connectStmt4.getTree());

                    }
                    break;
                case 2 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:73:7: exitStmt
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_exitStmt_in_stmt237);
                    exitStmt5=exitStmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, exitStmt5.getTree());

                    }
                    break;
                case 3 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:74:7: countStmt
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_countStmt_in_stmt245);
                    countStmt6=countStmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, countStmt6.getTree());

                    }
                    break;
                case 4 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:75:7: describeTable
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_describeTable_in_stmt253);
                    describeTable7=describeTable();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, describeTable7.getTree());

                    }
                    break;
                case 5 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:76:7: delStmt
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_delStmt_in_stmt261);
                    delStmt8=delStmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, delStmt8.getTree());

                    }
                    break;
                case 6 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:77:7: getStmt
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_getStmt_in_stmt269);
                    getStmt9=getStmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, getStmt9.getTree());

                    }
                    break;
                case 7 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:78:7: helpStmt
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_helpStmt_in_stmt277);
                    helpStmt10=helpStmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, helpStmt10.getTree());

                    }
                    break;
                case 8 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:79:7: setStmt
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_setStmt_in_stmt285);
                    setStmt11=setStmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, setStmt11.getTree());

                    }
                    break;
                case 9 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:80:7: showStmt
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_showStmt_in_stmt293);
                    showStmt12=showStmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, showStmt12.getTree());

                    }
                    break;
                case 10 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:81:7: 
                    {

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 81:7: -> ^( NODE_NO_OP )
                    {
                        // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:81:10: ^( NODE_NO_OP )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_NO_OP, "NODE_NO_OP"), root_1);

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "stmt"

    public static class connectStmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "connectStmt"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:84:1: connectStmt : ( K_CONNECT host SLASH port -> ^( NODE_CONNECT host port ) | K_CONNECT ipaddr SLASH port -> ^( NODE_CONNECT ipaddr port ) );
    public final CliParser.connectStmt_return connectStmt() throws RecognitionException {
        CliParser.connectStmt_return retval = new CliParser.connectStmt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token K_CONNECT13=null;
        Token SLASH15=null;
        Token K_CONNECT17=null;
        Token SLASH19=null;
        CliParser.host_return host14 = null;

        CliParser.port_return port16 = null;

        CliParser.ipaddr_return ipaddr18 = null;

        CliParser.port_return port20 = null;


        CommonTree K_CONNECT13_tree=null;
        CommonTree SLASH15_tree=null;
        CommonTree K_CONNECT17_tree=null;
        CommonTree SLASH19_tree=null;
        RewriteRuleTokenStream stream_SLASH=new RewriteRuleTokenStream(adaptor,"token SLASH");
        RewriteRuleTokenStream stream_K_CONNECT=new RewriteRuleTokenStream(adaptor,"token K_CONNECT");
        RewriteRuleSubtreeStream stream_port=new RewriteRuleSubtreeStream(adaptor,"rule port");
        RewriteRuleSubtreeStream stream_ipaddr=new RewriteRuleSubtreeStream(adaptor,"rule ipaddr");
        RewriteRuleSubtreeStream stream_host=new RewriteRuleSubtreeStream(adaptor,"rule host");
        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:85:5: ( K_CONNECT host SLASH port -> ^( NODE_CONNECT host port ) | K_CONNECT ipaddr SLASH port -> ^( NODE_CONNECT ipaddr port ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==K_CONNECT) ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1==IntegerLiteral) ) {
                    alt3=2;
                }
                else if ( (LA3_1==Identifier) ) {
                    alt3=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:85:7: K_CONNECT host SLASH port
                    {
                    K_CONNECT13=(Token)match(input,K_CONNECT,FOLLOW_K_CONNECT_in_connectStmt322); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_K_CONNECT.add(K_CONNECT13);

                    pushFollow(FOLLOW_host_in_connectStmt324);
                    host14=host();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_host.add(host14.getTree());
                    SLASH15=(Token)match(input,SLASH,FOLLOW_SLASH_in_connectStmt326); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SLASH.add(SLASH15);

                    pushFollow(FOLLOW_port_in_connectStmt328);
                    port16=port();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_port.add(port16.getTree());


                    // AST REWRITE
                    // elements: host, port
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 85:33: -> ^( NODE_CONNECT host port )
                    {
                        // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:85:36: ^( NODE_CONNECT host port )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_CONNECT, "NODE_CONNECT"), root_1);

                        adaptor.addChild(root_1, stream_host.nextTree());
                        adaptor.addChild(root_1, stream_port.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:86:7: K_CONNECT ipaddr SLASH port
                    {
                    K_CONNECT17=(Token)match(input,K_CONNECT,FOLLOW_K_CONNECT_in_connectStmt346); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_K_CONNECT.add(K_CONNECT17);

                    pushFollow(FOLLOW_ipaddr_in_connectStmt348);
                    ipaddr18=ipaddr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ipaddr.add(ipaddr18.getTree());
                    SLASH19=(Token)match(input,SLASH,FOLLOW_SLASH_in_connectStmt350); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SLASH.add(SLASH19);

                    pushFollow(FOLLOW_port_in_connectStmt352);
                    port20=port();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_port.add(port20.getTree());


                    // AST REWRITE
                    // elements: port, ipaddr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 86:35: -> ^( NODE_CONNECT ipaddr port )
                    {
                        // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:86:38: ^( NODE_CONNECT ipaddr port )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_CONNECT, "NODE_CONNECT"), root_1);

                        adaptor.addChild(root_1, stream_ipaddr.nextTree());
                        adaptor.addChild(root_1, stream_port.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "connectStmt"

    public static class helpStmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "helpStmt"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:89:1: helpStmt : ( K_HELP -> ^( NODE_HELP ) | '?' -> ^( NODE_HELP ) );
    public final CliParser.helpStmt_return helpStmt() throws RecognitionException {
        CliParser.helpStmt_return retval = new CliParser.helpStmt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token K_HELP21=null;
        Token char_literal22=null;

        CommonTree K_HELP21_tree=null;
        CommonTree char_literal22_tree=null;
        RewriteRuleTokenStream stream_K_HELP=new RewriteRuleTokenStream(adaptor,"token K_HELP");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");

        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:90:5: ( K_HELP -> ^( NODE_HELP ) | '?' -> ^( NODE_HELP ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==K_HELP) ) {
                alt4=1;
            }
            else if ( (LA4_0==47) ) {
                alt4=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:90:7: K_HELP
                    {
                    K_HELP21=(Token)match(input,K_HELP,FOLLOW_K_HELP_in_helpStmt379); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_K_HELP.add(K_HELP21);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 90:14: -> ^( NODE_HELP )
                    {
                        // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:90:17: ^( NODE_HELP )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_HELP, "NODE_HELP"), root_1);

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:91:7: '?'
                    {
                    char_literal22=(Token)match(input,47,FOLLOW_47_in_helpStmt393); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal22);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 91:14: -> ^( NODE_HELP )
                    {
                        // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:91:17: ^( NODE_HELP )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_HELP, "NODE_HELP"), root_1);

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "helpStmt"

    public static class exitStmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exitStmt"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:94:1: exitStmt : ( K_QUIT -> ^( NODE_EXIT ) | K_EXIT -> ^( NODE_EXIT ) );
    public final CliParser.exitStmt_return exitStmt() throws RecognitionException {
        CliParser.exitStmt_return retval = new CliParser.exitStmt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token K_QUIT23=null;
        Token K_EXIT24=null;

        CommonTree K_QUIT23_tree=null;
        CommonTree K_EXIT24_tree=null;
        RewriteRuleTokenStream stream_K_EXIT=new RewriteRuleTokenStream(adaptor,"token K_EXIT");
        RewriteRuleTokenStream stream_K_QUIT=new RewriteRuleTokenStream(adaptor,"token K_QUIT");

        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:95:5: ( K_QUIT -> ^( NODE_EXIT ) | K_EXIT -> ^( NODE_EXIT ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==K_QUIT) ) {
                alt5=1;
            }
            else if ( (LA5_0==K_EXIT) ) {
                alt5=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:95:7: K_QUIT
                    {
                    K_QUIT23=(Token)match(input,K_QUIT,FOLLOW_K_QUIT_in_exitStmt419); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_K_QUIT.add(K_QUIT23);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 95:14: -> ^( NODE_EXIT )
                    {
                        // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:95:17: ^( NODE_EXIT )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_EXIT, "NODE_EXIT"), root_1);

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:96:7: K_EXIT
                    {
                    K_EXIT24=(Token)match(input,K_EXIT,FOLLOW_K_EXIT_in_exitStmt433); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_K_EXIT.add(K_EXIT24);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 96:14: -> ^( NODE_EXIT )
                    {
                        // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:96:17: ^( NODE_EXIT )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_EXIT, "NODE_EXIT"), root_1);

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "exitStmt"

    public static class getStmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "getStmt"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:99:1: getStmt : K_GET columnFamilyExpr -> ^( NODE_THRIFT_GET columnFamilyExpr ) ;
    public final CliParser.getStmt_return getStmt() throws RecognitionException {
        CliParser.getStmt_return retval = new CliParser.getStmt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token K_GET25=null;
        CliParser.columnFamilyExpr_return columnFamilyExpr26 = null;


        CommonTree K_GET25_tree=null;
        RewriteRuleTokenStream stream_K_GET=new RewriteRuleTokenStream(adaptor,"token K_GET");
        RewriteRuleSubtreeStream stream_columnFamilyExpr=new RewriteRuleSubtreeStream(adaptor,"rule columnFamilyExpr");
        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:100:5: ( K_GET columnFamilyExpr -> ^( NODE_THRIFT_GET columnFamilyExpr ) )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:100:7: K_GET columnFamilyExpr
            {
            K_GET25=(Token)match(input,K_GET,FOLLOW_K_GET_in_getStmt456); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_K_GET.add(K_GET25);

            pushFollow(FOLLOW_columnFamilyExpr_in_getStmt458);
            columnFamilyExpr26=columnFamilyExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_columnFamilyExpr.add(columnFamilyExpr26.getTree());


            // AST REWRITE
            // elements: columnFamilyExpr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 100:30: -> ^( NODE_THRIFT_GET columnFamilyExpr )
            {
                // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:100:33: ^( NODE_THRIFT_GET columnFamilyExpr )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_THRIFT_GET, "NODE_THRIFT_GET"), root_1);

                adaptor.addChild(root_1, stream_columnFamilyExpr.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "getStmt"

    public static class setStmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "setStmt"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:103:1: setStmt : K_SET columnFamilyExpr '=' value -> ^( NODE_THRIFT_SET columnFamilyExpr value ) ;
    public final CliParser.setStmt_return setStmt() throws RecognitionException {
        CliParser.setStmt_return retval = new CliParser.setStmt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token K_SET27=null;
        Token char_literal29=null;
        CliParser.columnFamilyExpr_return columnFamilyExpr28 = null;

        CliParser.value_return value30 = null;


        CommonTree K_SET27_tree=null;
        CommonTree char_literal29_tree=null;
        RewriteRuleTokenStream stream_48=new RewriteRuleTokenStream(adaptor,"token 48");
        RewriteRuleTokenStream stream_K_SET=new RewriteRuleTokenStream(adaptor,"token K_SET");
        RewriteRuleSubtreeStream stream_columnFamilyExpr=new RewriteRuleSubtreeStream(adaptor,"rule columnFamilyExpr");
        RewriteRuleSubtreeStream stream_value=new RewriteRuleSubtreeStream(adaptor,"rule value");
        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:104:5: ( K_SET columnFamilyExpr '=' value -> ^( NODE_THRIFT_SET columnFamilyExpr value ) )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:104:7: K_SET columnFamilyExpr '=' value
            {
            K_SET27=(Token)match(input,K_SET,FOLLOW_K_SET_in_setStmt483); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_K_SET.add(K_SET27);

            pushFollow(FOLLOW_columnFamilyExpr_in_setStmt485);
            columnFamilyExpr28=columnFamilyExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_columnFamilyExpr.add(columnFamilyExpr28.getTree());
            char_literal29=(Token)match(input,48,FOLLOW_48_in_setStmt487); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_48.add(char_literal29);

            pushFollow(FOLLOW_value_in_setStmt489);
            value30=value();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_value.add(value30.getTree());


            // AST REWRITE
            // elements: value, columnFamilyExpr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 104:40: -> ^( NODE_THRIFT_SET columnFamilyExpr value )
            {
                // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:104:43: ^( NODE_THRIFT_SET columnFamilyExpr value )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_THRIFT_SET, "NODE_THRIFT_SET"), root_1);

                adaptor.addChild(root_1, stream_columnFamilyExpr.nextTree());
                adaptor.addChild(root_1, stream_value.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "setStmt"

    public static class countStmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "countStmt"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:107:1: countStmt : K_COUNT columnFamilyExpr -> ^( NODE_THRIFT_COUNT columnFamilyExpr ) ;
    public final CliParser.countStmt_return countStmt() throws RecognitionException {
        CliParser.countStmt_return retval = new CliParser.countStmt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token K_COUNT31=null;
        CliParser.columnFamilyExpr_return columnFamilyExpr32 = null;


        CommonTree K_COUNT31_tree=null;
        RewriteRuleTokenStream stream_K_COUNT=new RewriteRuleTokenStream(adaptor,"token K_COUNT");
        RewriteRuleSubtreeStream stream_columnFamilyExpr=new RewriteRuleSubtreeStream(adaptor,"rule columnFamilyExpr");
        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:108:5: ( K_COUNT columnFamilyExpr -> ^( NODE_THRIFT_COUNT columnFamilyExpr ) )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:108:7: K_COUNT columnFamilyExpr
            {
            K_COUNT31=(Token)match(input,K_COUNT,FOLLOW_K_COUNT_in_countStmt516); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_K_COUNT.add(K_COUNT31);

            pushFollow(FOLLOW_columnFamilyExpr_in_countStmt518);
            columnFamilyExpr32=columnFamilyExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_columnFamilyExpr.add(columnFamilyExpr32.getTree());


            // AST REWRITE
            // elements: columnFamilyExpr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 108:32: -> ^( NODE_THRIFT_COUNT columnFamilyExpr )
            {
                // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:108:35: ^( NODE_THRIFT_COUNT columnFamilyExpr )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_THRIFT_COUNT, "NODE_THRIFT_COUNT"), root_1);

                adaptor.addChild(root_1, stream_columnFamilyExpr.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "countStmt"

    public static class delStmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "delStmt"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:111:1: delStmt : K_DEL columnFamilyExpr -> ^( NODE_THRIFT_DEL columnFamilyExpr ) ;
    public final CliParser.delStmt_return delStmt() throws RecognitionException {
        CliParser.delStmt_return retval = new CliParser.delStmt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token K_DEL33=null;
        CliParser.columnFamilyExpr_return columnFamilyExpr34 = null;


        CommonTree K_DEL33_tree=null;
        RewriteRuleTokenStream stream_K_DEL=new RewriteRuleTokenStream(adaptor,"token K_DEL");
        RewriteRuleSubtreeStream stream_columnFamilyExpr=new RewriteRuleSubtreeStream(adaptor,"rule columnFamilyExpr");
        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:112:5: ( K_DEL columnFamilyExpr -> ^( NODE_THRIFT_DEL columnFamilyExpr ) )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:112:7: K_DEL columnFamilyExpr
            {
            K_DEL33=(Token)match(input,K_DEL,FOLLOW_K_DEL_in_delStmt543); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_K_DEL.add(K_DEL33);

            pushFollow(FOLLOW_columnFamilyExpr_in_delStmt545);
            columnFamilyExpr34=columnFamilyExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_columnFamilyExpr.add(columnFamilyExpr34.getTree());


            // AST REWRITE
            // elements: columnFamilyExpr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 112:30: -> ^( NODE_THRIFT_DEL columnFamilyExpr )
            {
                // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:112:33: ^( NODE_THRIFT_DEL columnFamilyExpr )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_THRIFT_DEL, "NODE_THRIFT_DEL"), root_1);

                adaptor.addChild(root_1, stream_columnFamilyExpr.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "delStmt"

    public static class showStmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "showStmt"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:115:1: showStmt : ( showClusterName | showVersion | showConfigFile | showTables );
    public final CliParser.showStmt_return showStmt() throws RecognitionException {
        CliParser.showStmt_return retval = new CliParser.showStmt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CliParser.showClusterName_return showClusterName35 = null;

        CliParser.showVersion_return showVersion36 = null;

        CliParser.showConfigFile_return showConfigFile37 = null;

        CliParser.showTables_return showTables38 = null;



        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:116:5: ( showClusterName | showVersion | showConfigFile | showTables )
            int alt6=4;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==K_SHOW) ) {
                switch ( input.LA(2) ) {
                case K_CLUSTER:
                    {
                    alt6=1;
                    }
                    break;
                case K_VERSION:
                    {
                    alt6=2;
                    }
                    break;
                case K_CONFIG:
                    {
                    alt6=3;
                    }
                    break;
                case K_TABLES:
                    {
                    alt6=4;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;
                }

            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:116:7: showClusterName
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_showClusterName_in_showStmt570);
                    showClusterName35=showClusterName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, showClusterName35.getTree());

                    }
                    break;
                case 2 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:117:7: showVersion
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_showVersion_in_showStmt578);
                    showVersion36=showVersion();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, showVersion36.getTree());

                    }
                    break;
                case 3 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:118:7: showConfigFile
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_showConfigFile_in_showStmt586);
                    showConfigFile37=showConfigFile();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, showConfigFile37.getTree());

                    }
                    break;
                case 4 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:119:7: showTables
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_showTables_in_showStmt594);
                    showTables38=showTables();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, showTables38.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "showStmt"

    public static class showClusterName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "showClusterName"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:122:1: showClusterName : K_SHOW K_CLUSTER K_NAME -> ^( NODE_SHOW_CLUSTER_NAME ) ;
    public final CliParser.showClusterName_return showClusterName() throws RecognitionException {
        CliParser.showClusterName_return retval = new CliParser.showClusterName_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token K_SHOW39=null;
        Token K_CLUSTER40=null;
        Token K_NAME41=null;

        CommonTree K_SHOW39_tree=null;
        CommonTree K_CLUSTER40_tree=null;
        CommonTree K_NAME41_tree=null;
        RewriteRuleTokenStream stream_K_SHOW=new RewriteRuleTokenStream(adaptor,"token K_SHOW");
        RewriteRuleTokenStream stream_K_NAME=new RewriteRuleTokenStream(adaptor,"token K_NAME");
        RewriteRuleTokenStream stream_K_CLUSTER=new RewriteRuleTokenStream(adaptor,"token K_CLUSTER");

        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:123:5: ( K_SHOW K_CLUSTER K_NAME -> ^( NODE_SHOW_CLUSTER_NAME ) )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:123:7: K_SHOW K_CLUSTER K_NAME
            {
            K_SHOW39=(Token)match(input,K_SHOW,FOLLOW_K_SHOW_in_showClusterName611); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_K_SHOW.add(K_SHOW39);

            K_CLUSTER40=(Token)match(input,K_CLUSTER,FOLLOW_K_CLUSTER_in_showClusterName613); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_K_CLUSTER.add(K_CLUSTER40);

            K_NAME41=(Token)match(input,K_NAME,FOLLOW_K_NAME_in_showClusterName615); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_K_NAME.add(K_NAME41);



            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 123:31: -> ^( NODE_SHOW_CLUSTER_NAME )
            {
                // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:123:34: ^( NODE_SHOW_CLUSTER_NAME )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_SHOW_CLUSTER_NAME, "NODE_SHOW_CLUSTER_NAME"), root_1);

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "showClusterName"

    public static class showConfigFile_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "showConfigFile"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:126:1: showConfigFile : K_SHOW K_CONFIG K_FILE -> ^( NODE_SHOW_CONFIG_FILE ) ;
    public final CliParser.showConfigFile_return showConfigFile() throws RecognitionException {
        CliParser.showConfigFile_return retval = new CliParser.showConfigFile_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token K_SHOW42=null;
        Token K_CONFIG43=null;
        Token K_FILE44=null;

        CommonTree K_SHOW42_tree=null;
        CommonTree K_CONFIG43_tree=null;
        CommonTree K_FILE44_tree=null;
        RewriteRuleTokenStream stream_K_SHOW=new RewriteRuleTokenStream(adaptor,"token K_SHOW");
        RewriteRuleTokenStream stream_K_FILE=new RewriteRuleTokenStream(adaptor,"token K_FILE");
        RewriteRuleTokenStream stream_K_CONFIG=new RewriteRuleTokenStream(adaptor,"token K_CONFIG");

        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:127:5: ( K_SHOW K_CONFIG K_FILE -> ^( NODE_SHOW_CONFIG_FILE ) )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:127:7: K_SHOW K_CONFIG K_FILE
            {
            K_SHOW42=(Token)match(input,K_SHOW,FOLLOW_K_SHOW_in_showConfigFile638); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_K_SHOW.add(K_SHOW42);

            K_CONFIG43=(Token)match(input,K_CONFIG,FOLLOW_K_CONFIG_in_showConfigFile640); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_K_CONFIG.add(K_CONFIG43);

            K_FILE44=(Token)match(input,K_FILE,FOLLOW_K_FILE_in_showConfigFile642); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_K_FILE.add(K_FILE44);



            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 127:30: -> ^( NODE_SHOW_CONFIG_FILE )
            {
                // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:127:33: ^( NODE_SHOW_CONFIG_FILE )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_SHOW_CONFIG_FILE, "NODE_SHOW_CONFIG_FILE"), root_1);

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "showConfigFile"

    public static class showVersion_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "showVersion"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:130:1: showVersion : K_SHOW K_VERSION -> ^( NODE_SHOW_VERSION ) ;
    public final CliParser.showVersion_return showVersion() throws RecognitionException {
        CliParser.showVersion_return retval = new CliParser.showVersion_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token K_SHOW45=null;
        Token K_VERSION46=null;

        CommonTree K_SHOW45_tree=null;
        CommonTree K_VERSION46_tree=null;
        RewriteRuleTokenStream stream_K_SHOW=new RewriteRuleTokenStream(adaptor,"token K_SHOW");
        RewriteRuleTokenStream stream_K_VERSION=new RewriteRuleTokenStream(adaptor,"token K_VERSION");

        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:131:5: ( K_SHOW K_VERSION -> ^( NODE_SHOW_VERSION ) )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:131:7: K_SHOW K_VERSION
            {
            K_SHOW45=(Token)match(input,K_SHOW,FOLLOW_K_SHOW_in_showVersion665); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_K_SHOW.add(K_SHOW45);

            K_VERSION46=(Token)match(input,K_VERSION,FOLLOW_K_VERSION_in_showVersion667); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_K_VERSION.add(K_VERSION46);



            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 131:24: -> ^( NODE_SHOW_VERSION )
            {
                // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:131:27: ^( NODE_SHOW_VERSION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_SHOW_VERSION, "NODE_SHOW_VERSION"), root_1);

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "showVersion"

    public static class showTables_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "showTables"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:134:1: showTables : K_SHOW K_TABLES -> ^( NODE_SHOW_TABLES ) ;
    public final CliParser.showTables_return showTables() throws RecognitionException {
        CliParser.showTables_return retval = new CliParser.showTables_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token K_SHOW47=null;
        Token K_TABLES48=null;

        CommonTree K_SHOW47_tree=null;
        CommonTree K_TABLES48_tree=null;
        RewriteRuleTokenStream stream_K_SHOW=new RewriteRuleTokenStream(adaptor,"token K_SHOW");
        RewriteRuleTokenStream stream_K_TABLES=new RewriteRuleTokenStream(adaptor,"token K_TABLES");

        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:135:5: ( K_SHOW K_TABLES -> ^( NODE_SHOW_TABLES ) )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:135:7: K_SHOW K_TABLES
            {
            K_SHOW47=(Token)match(input,K_SHOW,FOLLOW_K_SHOW_in_showTables690); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_K_SHOW.add(K_SHOW47);

            K_TABLES48=(Token)match(input,K_TABLES,FOLLOW_K_TABLES_in_showTables692); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_K_TABLES.add(K_TABLES48);



            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 135:23: -> ^( NODE_SHOW_TABLES )
            {
                // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:135:26: ^( NODE_SHOW_TABLES )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_SHOW_TABLES, "NODE_SHOW_TABLES"), root_1);

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "showTables"

    public static class describeTable_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "describeTable"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:138:1: describeTable : K_DESCRIBE K_TABLE table -> ^( NODE_DESCRIBE_TABLE table ) ;
    public final CliParser.describeTable_return describeTable() throws RecognitionException {
        CliParser.describeTable_return retval = new CliParser.describeTable_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token K_DESCRIBE49=null;
        Token K_TABLE50=null;
        CliParser.table_return table51 = null;


        CommonTree K_DESCRIBE49_tree=null;
        CommonTree K_TABLE50_tree=null;
        RewriteRuleTokenStream stream_K_DESCRIBE=new RewriteRuleTokenStream(adaptor,"token K_DESCRIBE");
        RewriteRuleTokenStream stream_K_TABLE=new RewriteRuleTokenStream(adaptor,"token K_TABLE");
        RewriteRuleSubtreeStream stream_table=new RewriteRuleSubtreeStream(adaptor,"rule table");
        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:139:5: ( K_DESCRIBE K_TABLE table -> ^( NODE_DESCRIBE_TABLE table ) )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:139:7: K_DESCRIBE K_TABLE table
            {
            K_DESCRIBE49=(Token)match(input,K_DESCRIBE,FOLLOW_K_DESCRIBE_in_describeTable715); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_K_DESCRIBE.add(K_DESCRIBE49);

            K_TABLE50=(Token)match(input,K_TABLE,FOLLOW_K_TABLE_in_describeTable717); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_K_TABLE.add(K_TABLE50);

            pushFollow(FOLLOW_table_in_describeTable719);
            table51=table();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_table.add(table51.getTree());


            // AST REWRITE
            // elements: table
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 139:32: -> ^( NODE_DESCRIBE_TABLE table )
            {
                // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:139:35: ^( NODE_DESCRIBE_TABLE table )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_DESCRIBE_TABLE, "NODE_DESCRIBE_TABLE"), root_1);

                adaptor.addChild(root_1, stream_table.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "describeTable"

    public static class columnFamilyExpr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "columnFamilyExpr"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:141:1: columnFamilyExpr : table DOT columnFamily '[' rowKey ']' ( '[' a+= columnOrSuperColumn ']' ( '[' a+= columnOrSuperColumn ']' )? )? -> ^( NODE_COLUMN_ACCESS table columnFamily rowKey ( ( $a)+ )? ) ;
    public final CliParser.columnFamilyExpr_return columnFamilyExpr() throws RecognitionException {
        CliParser.columnFamilyExpr_return retval = new CliParser.columnFamilyExpr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token DOT53=null;
        Token char_literal55=null;
        Token char_literal57=null;
        Token char_literal58=null;
        Token char_literal59=null;
        Token char_literal60=null;
        Token char_literal61=null;
        List list_a=null;
        CliParser.table_return table52 = null;

        CliParser.columnFamily_return columnFamily54 = null;

        CliParser.rowKey_return rowKey56 = null;

        CliParser.columnOrSuperColumn_return a = null;
         a = null;
        CommonTree DOT53_tree=null;
        CommonTree char_literal55_tree=null;
        CommonTree char_literal57_tree=null;
        CommonTree char_literal58_tree=null;
        CommonTree char_literal59_tree=null;
        CommonTree char_literal60_tree=null;
        CommonTree char_literal61_tree=null;
        RewriteRuleTokenStream stream_49=new RewriteRuleTokenStream(adaptor,"token 49");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleTokenStream stream_50=new RewriteRuleTokenStream(adaptor,"token 50");
        RewriteRuleSubtreeStream stream_columnFamily=new RewriteRuleSubtreeStream(adaptor,"rule columnFamily");
        RewriteRuleSubtreeStream stream_rowKey=new RewriteRuleSubtreeStream(adaptor,"rule rowKey");
        RewriteRuleSubtreeStream stream_table=new RewriteRuleSubtreeStream(adaptor,"rule table");
        RewriteRuleSubtreeStream stream_columnOrSuperColumn=new RewriteRuleSubtreeStream(adaptor,"rule columnOrSuperColumn");
        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:142:5: ( table DOT columnFamily '[' rowKey ']' ( '[' a+= columnOrSuperColumn ']' ( '[' a+= columnOrSuperColumn ']' )? )? -> ^( NODE_COLUMN_ACCESS table columnFamily rowKey ( ( $a)+ )? ) )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:142:7: table DOT columnFamily '[' rowKey ']' ( '[' a+= columnOrSuperColumn ']' ( '[' a+= columnOrSuperColumn ']' )? )?
            {
            pushFollow(FOLLOW_table_in_columnFamilyExpr739);
            table52=table();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_table.add(table52.getTree());
            DOT53=(Token)match(input,DOT,FOLLOW_DOT_in_columnFamilyExpr741); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_DOT.add(DOT53);

            pushFollow(FOLLOW_columnFamily_in_columnFamilyExpr743);
            columnFamily54=columnFamily();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_columnFamily.add(columnFamily54.getTree());
            char_literal55=(Token)match(input,49,FOLLOW_49_in_columnFamilyExpr745); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_49.add(char_literal55);

            pushFollow(FOLLOW_rowKey_in_columnFamilyExpr747);
            rowKey56=rowKey();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rowKey.add(rowKey56.getTree());
            char_literal57=(Token)match(input,50,FOLLOW_50_in_columnFamilyExpr749); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_50.add(char_literal57);

            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:143:9: ( '[' a+= columnOrSuperColumn ']' ( '[' a+= columnOrSuperColumn ']' )? )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==49) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:143:11: '[' a+= columnOrSuperColumn ']' ( '[' a+= columnOrSuperColumn ']' )?
                    {
                    char_literal58=(Token)match(input,49,FOLLOW_49_in_columnFamilyExpr762); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_49.add(char_literal58);

                    pushFollow(FOLLOW_columnOrSuperColumn_in_columnFamilyExpr766);
                    a=columnOrSuperColumn();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_columnOrSuperColumn.add(a.getTree());
                    if (list_a==null) list_a=new ArrayList();
                    list_a.add(a.getTree());

                    char_literal59=(Token)match(input,50,FOLLOW_50_in_columnFamilyExpr768); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_50.add(char_literal59);

                    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:144:13: ( '[' a+= columnOrSuperColumn ']' )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==49) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:144:14: '[' a+= columnOrSuperColumn ']'
                            {
                            char_literal60=(Token)match(input,49,FOLLOW_49_in_columnFamilyExpr784); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_49.add(char_literal60);

                            pushFollow(FOLLOW_columnOrSuperColumn_in_columnFamilyExpr788);
                            a=columnOrSuperColumn();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_columnOrSuperColumn.add(a.getTree());
                            if (list_a==null) list_a=new ArrayList();
                            list_a.add(a.getTree());

                            char_literal61=(Token)match(input,50,FOLLOW_50_in_columnFamilyExpr790); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_50.add(char_literal61);


                            }
                            break;

                    }


                    }
                    break;

            }



            // AST REWRITE
            // elements: columnFamily, rowKey, table, a
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: a
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_a=new RewriteRuleSubtreeStream(adaptor,"token a",list_a);
            root_0 = (CommonTree)adaptor.nil();
            // 146:7: -> ^( NODE_COLUMN_ACCESS table columnFamily rowKey ( ( $a)+ )? )
            {
                // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:146:10: ^( NODE_COLUMN_ACCESS table columnFamily rowKey ( ( $a)+ )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_COLUMN_ACCESS, "NODE_COLUMN_ACCESS"), root_1);

                adaptor.addChild(root_1, stream_table.nextTree());
                adaptor.addChild(root_1, stream_columnFamily.nextTree());
                adaptor.addChild(root_1, stream_rowKey.nextTree());
                // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:146:57: ( ( $a)+ )?
                if ( stream_a.hasNext() ) {
                    if ( !(stream_a.hasNext()) ) {
                        throw new RewriteEarlyExitException();
                    }
                    while ( stream_a.hasNext() ) {
                        adaptor.addChild(root_1, stream_a.nextTree());

                    }
                    stream_a.reset();

                }
                stream_a.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "columnFamilyExpr"

    public static class table_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "table"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:149:1: table : Identifier ;
    public final CliParser.table_return table() throws RecognitionException {
        CliParser.table_return retval = new CliParser.table_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token Identifier62=null;

        CommonTree Identifier62_tree=null;

        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:149:6: ( Identifier )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:149:8: Identifier
            {
            root_0 = (CommonTree)adaptor.nil();

            Identifier62=(Token)match(input,Identifier,FOLLOW_Identifier_in_table841); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier62_tree = (CommonTree)adaptor.create(Identifier62);
            adaptor.addChild(root_0, Identifier62_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "table"

    public static class columnFamily_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "columnFamily"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:151:1: columnFamily : Identifier ;
    public final CliParser.columnFamily_return columnFamily() throws RecognitionException {
        CliParser.columnFamily_return retval = new CliParser.columnFamily_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token Identifier63=null;

        CommonTree Identifier63_tree=null;

        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:151:13: ( Identifier )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:151:15: Identifier
            {
            root_0 = (CommonTree)adaptor.nil();

            Identifier63=(Token)match(input,Identifier,FOLLOW_Identifier_in_columnFamily848); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier63_tree = (CommonTree)adaptor.create(Identifier63);
            adaptor.addChild(root_0, Identifier63_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "columnFamily"

    public static class rowKey_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rowKey"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:153:1: rowKey : StringLiteral ;
    public final CliParser.rowKey_return rowKey() throws RecognitionException {
        CliParser.rowKey_return retval = new CliParser.rowKey_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token StringLiteral64=null;

        CommonTree StringLiteral64_tree=null;

        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:153:7: ( StringLiteral )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:153:11: StringLiteral
            {
            root_0 = (CommonTree)adaptor.nil();

            StringLiteral64=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_rowKey857); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            StringLiteral64_tree = (CommonTree)adaptor.create(StringLiteral64);
            adaptor.addChild(root_0, StringLiteral64_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rowKey"

    public static class value_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "value"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:155:1: value : StringLiteral ;
    public final CliParser.value_return value() throws RecognitionException {
        CliParser.value_return retval = new CliParser.value_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token StringLiteral65=null;

        CommonTree StringLiteral65_tree=null;

        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:155:6: ( StringLiteral )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:155:8: StringLiteral
            {
            root_0 = (CommonTree)adaptor.nil();

            StringLiteral65=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_value864); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            StringLiteral65_tree = (CommonTree)adaptor.create(StringLiteral65);
            adaptor.addChild(root_0, StringLiteral65_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "value"

    public static class columnOrSuperColumn_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "columnOrSuperColumn"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:157:1: columnOrSuperColumn : StringLiteral ;
    public final CliParser.columnOrSuperColumn_return columnOrSuperColumn() throws RecognitionException {
        CliParser.columnOrSuperColumn_return retval = new CliParser.columnOrSuperColumn_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token StringLiteral66=null;

        CommonTree StringLiteral66_tree=null;

        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:157:20: ( StringLiteral )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:157:22: StringLiteral
            {
            root_0 = (CommonTree)adaptor.nil();

            StringLiteral66=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_columnOrSuperColumn871); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            StringLiteral66_tree = (CommonTree)adaptor.create(StringLiteral66);
            adaptor.addChild(root_0, StringLiteral66_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "columnOrSuperColumn"

    public static class host_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "host"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:159:1: host : id+= Identifier (id+= DOT id+= Identifier )* -> ^( NODE_ID_LIST ( $id)+ ) ;
    public final CliParser.host_return host() throws RecognitionException {
        CliParser.host_return retval = new CliParser.host_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token id=null;
        List list_id=null;

        CommonTree id_tree=null;
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");

        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:159:5: (id+= Identifier (id+= DOT id+= Identifier )* -> ^( NODE_ID_LIST ( $id)+ ) )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:159:7: id+= Identifier (id+= DOT id+= Identifier )*
            {
            id=(Token)match(input,Identifier,FOLLOW_Identifier_in_host880); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(id);

            if (list_id==null) list_id=new ArrayList();
            list_id.add(id);

            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:159:22: (id+= DOT id+= Identifier )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==DOT) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:159:23: id+= DOT id+= Identifier
            	    {
            	    id=(Token)match(input,DOT,FOLLOW_DOT_in_host885); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_DOT.add(id);

            	    if (list_id==null) list_id=new ArrayList();
            	    list_id.add(id);

            	    id=(Token)match(input,Identifier,FOLLOW_Identifier_in_host889); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Identifier.add(id);

            	    if (list_id==null) list_id=new ArrayList();
            	    list_id.add(id);


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);



            // AST REWRITE
            // elements: id
            // token labels: 
            // rule labels: retval
            // token list labels: id
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id", list_id);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 159:48: -> ^( NODE_ID_LIST ( $id)+ )
            {
                // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:159:51: ^( NODE_ID_LIST ( $id)+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_ID_LIST, "NODE_ID_LIST"), root_1);

                if ( !(stream_id.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_id.hasNext() ) {
                    adaptor.addChild(root_1, stream_id.nextNode());

                }
                stream_id.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "host"

    public static class ipaddr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ipaddr"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:161:1: ipaddr : id+= IntegerLiteral id+= DOT id+= IntegerLiteral id+= DOT id+= IntegerLiteral id+= DOT id+= IntegerLiteral -> ^( NODE_ID_LIST ( $id)+ ) ;
    public final CliParser.ipaddr_return ipaddr() throws RecognitionException {
        CliParser.ipaddr_return retval = new CliParser.ipaddr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token id=null;
        List list_id=null;

        CommonTree id_tree=null;
        RewriteRuleTokenStream stream_IntegerLiteral=new RewriteRuleTokenStream(adaptor,"token IntegerLiteral");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");

        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:161:7: (id+= IntegerLiteral id+= DOT id+= IntegerLiteral id+= DOT id+= IntegerLiteral id+= DOT id+= IntegerLiteral -> ^( NODE_ID_LIST ( $id)+ ) )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:161:9: id+= IntegerLiteral id+= DOT id+= IntegerLiteral id+= DOT id+= IntegerLiteral id+= DOT id+= IntegerLiteral
            {
            id=(Token)match(input,IntegerLiteral,FOLLOW_IntegerLiteral_in_ipaddr910); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IntegerLiteral.add(id);

            if (list_id==null) list_id=new ArrayList();
            list_id.add(id);

            id=(Token)match(input,DOT,FOLLOW_DOT_in_ipaddr914); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_DOT.add(id);

            if (list_id==null) list_id=new ArrayList();
            list_id.add(id);

            id=(Token)match(input,IntegerLiteral,FOLLOW_IntegerLiteral_in_ipaddr918); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IntegerLiteral.add(id);

            if (list_id==null) list_id=new ArrayList();
            list_id.add(id);

            id=(Token)match(input,DOT,FOLLOW_DOT_in_ipaddr922); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_DOT.add(id);

            if (list_id==null) list_id=new ArrayList();
            list_id.add(id);

            id=(Token)match(input,IntegerLiteral,FOLLOW_IntegerLiteral_in_ipaddr926); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IntegerLiteral.add(id);

            if (list_id==null) list_id=new ArrayList();
            list_id.add(id);

            id=(Token)match(input,DOT,FOLLOW_DOT_in_ipaddr930); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_DOT.add(id);

            if (list_id==null) list_id=new ArrayList();
            list_id.add(id);

            id=(Token)match(input,IntegerLiteral,FOLLOW_IntegerLiteral_in_ipaddr934); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IntegerLiteral.add(id);

            if (list_id==null) list_id=new ArrayList();
            list_id.add(id);



            // AST REWRITE
            // elements: id
            // token labels: 
            // rule labels: retval
            // token list labels: id
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id", list_id);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 161:109: -> ^( NODE_ID_LIST ( $id)+ )
            {
                // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:161:112: ^( NODE_ID_LIST ( $id)+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(NODE_ID_LIST, "NODE_ID_LIST"), root_1);

                if ( !(stream_id.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_id.hasNext() ) {
                    adaptor.addChild(root_1, stream_id.nextNode());

                }
                stream_id.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ipaddr"

    public static class port_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "port"
    // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:163:1: port : IntegerLiteral ;
    public final CliParser.port_return port() throws RecognitionException {
        CliParser.port_return retval = new CliParser.port_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IntegerLiteral67=null;

        CommonTree IntegerLiteral67_tree=null;

        try {
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:163:5: ( IntegerLiteral )
            // D:\\Java\\lib\\apache-cassandra-0.5.1-src/src/java/org/apache/cassandra/cli/Cli.g:163:7: IntegerLiteral
            {
            root_0 = (CommonTree)adaptor.nil();

            IntegerLiteral67=(Token)match(input,IntegerLiteral,FOLLOW_IntegerLiteral_in_port951); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IntegerLiteral67_tree = (CommonTree)adaptor.create(IntegerLiteral67);
            adaptor.addChild(root_0, IntegerLiteral67_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "port"

    // Delegated rules


 

    public static final BitSet FOLLOW_stmt_in_root208 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_SEMICOLON_in_root210 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_root213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_connectStmt_in_stmt229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exitStmt_in_stmt237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_countStmt_in_stmt245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_describeTable_in_stmt253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_delStmt_in_stmt261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_getStmt_in_stmt269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_helpStmt_in_stmt277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_setStmt_in_stmt285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_showStmt_in_stmt293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_CONNECT_in_connectStmt322 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_host_in_connectStmt324 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_SLASH_in_connectStmt326 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_port_in_connectStmt328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_CONNECT_in_connectStmt346 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_ipaddr_in_connectStmt348 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_SLASH_in_connectStmt350 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_port_in_connectStmt352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_HELP_in_helpStmt379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_helpStmt393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_QUIT_in_exitStmt419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_EXIT_in_exitStmt433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_GET_in_getStmt456 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_columnFamilyExpr_in_getStmt458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_SET_in_setStmt483 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_columnFamilyExpr_in_setStmt485 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_setStmt487 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_value_in_setStmt489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_COUNT_in_countStmt516 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_columnFamilyExpr_in_countStmt518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_DEL_in_delStmt543 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_columnFamilyExpr_in_delStmt545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_showClusterName_in_showStmt570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_showVersion_in_showStmt578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_showConfigFile_in_showStmt586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_showTables_in_showStmt594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_SHOW_in_showClusterName611 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_K_CLUSTER_in_showClusterName613 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_K_NAME_in_showClusterName615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_SHOW_in_showConfigFile638 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_K_CONFIG_in_showConfigFile640 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_K_FILE_in_showConfigFile642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_SHOW_in_showVersion665 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_K_VERSION_in_showVersion667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_SHOW_in_showTables690 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_K_TABLES_in_showTables692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_K_DESCRIBE_in_describeTable715 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_K_TABLE_in_describeTable717 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_table_in_describeTable719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_table_in_columnFamilyExpr739 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_DOT_in_columnFamilyExpr741 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_columnFamily_in_columnFamilyExpr743 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_49_in_columnFamilyExpr745 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_rowKey_in_columnFamilyExpr747 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_columnFamilyExpr749 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_49_in_columnFamilyExpr762 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_columnOrSuperColumn_in_columnFamilyExpr766 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_columnFamilyExpr768 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_49_in_columnFamilyExpr784 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_columnOrSuperColumn_in_columnFamilyExpr788 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_columnFamilyExpr790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_table841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_columnFamily848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringLiteral_in_rowKey857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringLiteral_in_value864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringLiteral_in_columnOrSuperColumn871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_host880 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_DOT_in_host885 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_Identifier_in_host889 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_IntegerLiteral_in_ipaddr910 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_DOT_in_ipaddr914 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_IntegerLiteral_in_ipaddr918 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_DOT_in_ipaddr922 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_IntegerLiteral_in_ipaddr926 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_DOT_in_ipaddr930 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_IntegerLiteral_in_ipaddr934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IntegerLiteral_in_port951 = new BitSet(new long[]{0x0000000000000002L});

}