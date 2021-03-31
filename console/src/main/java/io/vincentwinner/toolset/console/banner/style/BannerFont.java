package io.vincentwinner.toolset.console.banner.style;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class BannerFont {
    public static final BannerFont ONE_ROW;
    public static final BannerFont THREE_D;
    public static final BannerFont THREE_D_ASCII;
    public static final BannerFont THREE_D_DIAGONAL;
    public static final BannerFont THREE_FIVE;
    public static final BannerFont FOUR_MAX;
    public static final BannerFont FIVE_LINE_OBLIQUE;
    public static final BannerFont AMC_3_LINE;
    public static final BannerFont AMC_3_LIV1;
    public static final BannerFont AMC_AAA01;
    public static final BannerFont AMC_NEKO;
    public static final BannerFont AMC_RAZOR;
    public static final BannerFont AMC_RAZOR2;
    public static final BannerFont AMC_SLASH;
    public static final BannerFont AMC_SLIDER;
    public static final BannerFont AMC_THIN;
    public static final BannerFont AMC_TUBES;
    public static final BannerFont AMC_UNTITLED;
    public static final BannerFont ANSI_SHADOW;
    public static final BannerFont ASCII_NEW_ROMAN;
    public static final BannerFont ACROBATIC;
    public static final BannerFont ALLIGATOR;
    public static final BannerFont ALLIGATOR2;
    public static final BannerFont ALPHA;
    public static final BannerFont ALPHABET;
    public static final BannerFont ARROWS;
    public static final BannerFont AVATAR;
    public static final BannerFont BANNER;
    public static final BannerFont BANNER3_D;
    public static final BannerFont BANNER3;
    public static final BannerFont BANNER4;
    public static final BannerFont BARBWIRE;
    public static final BannerFont BASIC;
    public static final BannerFont BEAR;
    public static final BannerFont BELL;
    public static final BannerFont BENJAMIN;
    public static final BannerFont BIG;
    public static final BannerFont BIG_CHIEF;
    public static final BannerFont BIG_MONEY_NE;
    public static final BannerFont BIG_MONEY_NW;
    public static final BannerFont BIG_MONEY_SE;
    public static final BannerFont BIG_MONEY_SW;
    public static final BannerFont BIGFIG;
    public static final BannerFont BINARY;
    public static final BannerFont BLOCK;
    public static final BannerFont BLOCKS;
    public static final BannerFont BLOODY;
    public static final BannerFont BOLGER;
    public static final BannerFont BRACED;
    public static final BannerFont BRIGHT;
    public static final BannerFont BROADWAY;
    public static final BannerFont BROADWAY_KB;
    public static final BannerFont BUBBLE;
    public static final BannerFont BULBHEAD;
    public static final BannerFont CALIGRAPHY;
    public static final BannerFont CALIGRAPHY2;
    public static final BannerFont CALVIN_S;
    public static final BannerFont CARDS;
    public static final BannerFont CATWALK;
    public static final BannerFont CHISELED;
    public static final BannerFont CHUNKY;
    public static final BannerFont COINSTAK;
    public static final BannerFont COLA;
    public static final BannerFont COLOSSAL;
    public static final BannerFont COMPUTER;
    public static final BannerFont CONTESSA;
    public static final BannerFont CONTRAST;
    public static final BannerFont COSMIKE;
    public static final BannerFont CRAWFORD;
    public static final BannerFont CRAWFORD2;
    public static final BannerFont CRAZY;
    public static final BannerFont CRICKET;
    public static final BannerFont CURSIVE;
    public static final BannerFont CYBERLARGE;
    public static final BannerFont CYBERMEDIUM;
    public static final BannerFont CYBERSMALL;
    public static final BannerFont CYGNET;
    public static final BannerFont DANC4;
    public static final BannerFont DWHISTLED;
    public static final BannerFont DANCING_FONT;
    public static final BannerFont DECIMAL;
    public static final BannerFont DEF_LEPPARD;
    public static final BannerFont DELTA_CORPS_PRIEST_1;
    public static final BannerFont DIAMOND;
    public static final BannerFont DIET_COLA;
    public static final BannerFont DIGITAL;
    public static final BannerFont DOH;
    public static final BannerFont DOOM;
    public static final BannerFont DOT_MATRIX;
    public static final BannerFont DOUBLE;
    public static final BannerFont DOUBLE_SHORTS;
    public static final BannerFont DR_PEPPER;
    public static final BannerFont EFTI_CHESS;
    public static final BannerFont EFTI_FONT;
    public static final BannerFont EFTI_ITALIC;
    public static final BannerFont EFTI_PITI;
    public static final BannerFont EFTI_ROBOT;
    public static final BannerFont EFTI_WALL;
    public static final BannerFont EFTI_WATER;
    public static final BannerFont ELECTRONIC;
    public static final BannerFont ELITE;
    public static final BannerFont EPIC;
    public static final BannerFont FENDER;
    public static final BannerFont FILTER;
    public static final BannerFont FIRE_FONT_K;
    public static final BannerFont FIRE_FONT_S;
    public static final BannerFont FLIPPED;
    public static final BannerFont FLOWER_POWER;
    public static final BannerFont FOUR_TOPS;
    public static final BannerFont FRAKTUR;
    public static final BannerFont FUN_FACE;
    public static final BannerFont FUN_FACES;
    public static final BannerFont FUZZY;
    public static final BannerFont GEORGI16;
    public static final BannerFont GEORGIA11;
    public static final BannerFont GHOST;
    public static final BannerFont GHOULISH;
    public static final BannerFont GLENYN;
    public static final BannerFont GOOFY;
    public static final BannerFont GOTHIC;
    public static final BannerFont GRACEFUL;
    public static final BannerFont GRADIENT;
    public static final BannerFont GRAFFITI;
    public static final BannerFont GREEK;
    public static final BannerFont HEART_LEFT;
    public static final BannerFont HEART_RIGHT;
    public static final BannerFont HENRY_3D;
    public static final BannerFont HEX;
    public static final BannerFont HIEROGLYPHS;
    public static final BannerFont HOLLYWOOD;
    public static final BannerFont HORIZONTAL_LEFT;
    public static final BannerFont HORIZONTAL_RIGHT;
    public static final BannerFont ICL_1900;
    public static final BannerFont IMPOSSIBLE;
    public static final BannerFont INVITA;
    public static final BannerFont ISOMETRIC1;
    public static final BannerFont ISOMETRIC2;
    public static final BannerFont ISOMETRIC3;
    public static final BannerFont ISOMETRIC4;
    public static final BannerFont ITALIC;
    public static final BannerFont IVRIT;
    public static final BannerFont JS_BLOCK_LETTERS;
    public static final BannerFont JS_BRACKET_LETTERS;
    public static final BannerFont JS_CAPITAL_CURVES;
    public static final BannerFont JS_CURSIVE;
    public static final BannerFont JS_STICK_LETTERS;
    public static final BannerFont JACKY;
    public static final BannerFont JAZMINE;
    public static final BannerFont JERUSALEM;
    public static final BannerFont KATAKANA;
    public static final BannerFont KBAN;
    public static final BannerFont KEYBOARD;
    public static final BannerFont KNOB;
    public static final BannerFont LCD;
    public static final BannerFont LARRY_3D;
    public static final BannerFont LEAN;
    public static final BannerFont LETTERS;
    public static final BannerFont LIL_DEVIL;
    public static final BannerFont LINE_BLOCKS;
    public static final BannerFont LINUX;
    public static final BannerFont LOCKERGNOME;
    public static final BannerFont MADRID;
    public static final BannerFont MARQUEE;
    public static final BannerFont MAXFOUR;
    public static final BannerFont MERLIN1;
    public static final BannerFont MERLIN2;
    public static final BannerFont MIKE;
    public static final BannerFont MINI;
    public static final BannerFont MIRROR;
    public static final BannerFont MNEMONIC;
    public static final BannerFont MODULAR;
    public static final BannerFont MORSE;
    public static final BannerFont MOSCOW;
    public static final BannerFont MSHEBREW210;
    public static final BannerFont MUZZLE;
    public static final BannerFont NSCRIPT;
    public static final BannerFont NT_GREEK;
    public static final BannerFont NV_SCRIPT;
    public static final BannerFont NANCYJ_FANCY;
    public static final BannerFont NANCYJ_UNDERLINED;
    public static final BannerFont NANCYJ;
    public static final BannerFont NIPPLES;
    public static final BannerFont O8;
    public static final BannerFont OS2;
    public static final BannerFont OCTAL;
    public static final BannerFont OGRE;
    public static final BannerFont OLD_BANNER;
    public static final BannerFont PATORJK_HEX;
    public static final BannerFont PATORJK_CHEESE;
    public static final BannerFont PAWP;
    public static final BannerFont PEAKS;
    public static final BannerFont PEAKS_SLANT;
    public static final BannerFont PEBBLES;
    public static final BannerFont PEPPER;
    public static final BannerFont POISON;
    public static final BannerFont PUFFY;
    public static final BannerFont PUZZLE;
    public static final BannerFont PYRAMID;
    public static final BannerFont RAMMSTEIN;
    public static final BannerFont RECTANGLES;
    public static final BannerFont RELIEF;
    public static final BannerFont RELIEF2;
    public static final BannerFont REVERSE;
    public static final BannerFont ROMAN;
    public static final BannerFont ROTATED;
    public static final BannerFont ROUNDED;
    public static final BannerFont ROWAN_CAP;
    public static final BannerFont ROZZO;
    public static final BannerFont RUNIC;
    public static final BannerFont RUNYC;
    public static final BannerFont SL_SCRIPT;
    public static final BannerFont S_BLOOD;
    public static final BannerFont SANTA_CLARA;
    public static final BannerFont SCRIPT;
    public static final BannerFont SERIFCAP;
    public static final BannerFont SHADOW;
    public static final BannerFont SHIMROD;
    public static final BannerFont SHORT;
    public static final BannerFont SLANT;
    public static final BannerFont SLANT_RELIEF;
    public static final BannerFont SLIDE;
    public static final BannerFont SMALL;
    public static final BannerFont SMALL_CAPS;
    public static final BannerFont SMALL_ISOMETRIC1;
    public static final BannerFont SMALL_KEYBOARD;
    public static final BannerFont SMALL_POISON;
    public static final BannerFont SMALL_SCRIPT;
    public static final BannerFont SMALL_SHADOW;
    public static final BannerFont SMALL_SLANT;
    public static final BannerFont SMALL_TENGWAR;
    public static final BannerFont SOFT;
    public static final BannerFont SPEED;
    public static final BannerFont SPLIFF;
    public static final BannerFont STACEY;
    public static final BannerFont STAMPATE;
    public static final BannerFont STAMPATELLO;
    public static final BannerFont STANDARD;
    public static final BannerFont STAR_STRIPS;
    public static final BannerFont STAR_WARS;
    public static final BannerFont STELLAR;
    public static final BannerFont STFOREK;
    public static final BannerFont STICK_LETTERS;
    public static final BannerFont STOP;
    public static final BannerFont STRAIGHT;
    public static final BannerFont STRONGER_THAN_ALL;
    public static final BannerFont SUB_ZERO;
    public static final BannerFont SWAMP_LAND;
    public static final BannerFont SWAN;
    public static final BannerFont SWEET;
    public static final BannerFont THIS;
    public static final BannerFont TANJA;
    public static final BannerFont TENGWAR;
    public static final BannerFont TERM;
    public static final BannerFont TEST1;
    public static final BannerFont THE_EDGE;
    public static final BannerFont THICK;
    public static final BannerFont THIN;
    public static final BannerFont THORNED;
    public static final BannerFont THREE_POINT;
    public static final BannerFont TICKS;
    public static final BannerFont TICKS_SLANT;
    public static final BannerFont TILES;
    public static final BannerFont TINKER_TOY;
    public static final BannerFont TOMBSTONE;
    public static final BannerFont TRAIN;
    public static final BannerFont TREK;
    public static final BannerFont TSALAGI;
    public static final BannerFont TUBULAR;
    public static final BannerFont TWISTED;
    public static final BannerFont TWO_POINT;
    public static final BannerFont USA_FLAG;
    public static final BannerFont UNIVERS;
    public static final BannerFont VARSITY;
    public static final BannerFont WAVY;
    public static final BannerFont WEIRD;
    public static final BannerFont WET_LETTER;
    public static final BannerFont WHIMSY;
    public static final BannerFont WOW;
    public static final BannerFont CIRCLE;
    public static final BannerFont EMBOSS;
    public static final BannerFont EMBOSS2;
    public static final BannerFont FUTURE;
    public static final BannerFont PAGGA;
    public static final BannerFont RUSTO;
    public static final BannerFont RUSTO_FAT;
    public static final BannerFont ASCII9;
    public static final BannerFont ASCII12;
    public static final BannerFont BIG_ASCII9;
    public static final BannerFont BIG_ASCII12;
    public static final BannerFont SMALL_ASCII9;
    public static final BannerFont SMALL_ASCII12;

    protected static final List<BannerFont> VALUES;
    protected static final Map<String, BannerFont> MAP;
    protected static final String ROOT_DIR_PATH = "console";
    protected static final String FONT_DIR_PATH = ROOT_DIR_PATH + "/fonts/";

    static {
        List<BannerFont> values = new ArrayList<>();
        values.add(ONE_ROW = new BannerFont("1Row", "1Row.flf"));
        values.add(THREE_D = new BannerFont("3-D", "3-D.flf"));
        values.add(THREE_D_ASCII = new BannerFont("3D-ASCII", "3D-ASCII.flf"));
        values.add(THREE_D_DIAGONAL = new BannerFont("3D Diagonal", "3D_Diagonal.flf"));
        values.add(THREE_FIVE = new BannerFont("3x5", "3x5.flf"));
        values.add(FOUR_MAX = new BannerFont("4Max", "4Max.flf"));
        values.add(FIVE_LINE_OBLIQUE = new BannerFont("5 Line Oblique", "5_Line_Oblique.flf"));
        values.add(AMC_3_LINE = new BannerFont("AMC 3 Line", "AMC_3_Line.flf"));
        values.add(AMC_3_LIV1 = new BannerFont("AMC 3 Liv1", "AMC_3_Liv1.flf"));
        values.add(AMC_AAA01 = new BannerFont("AMC AAA01", "AMC_AAA01.flf"));
        values.add(AMC_NEKO = new BannerFont("AMC Neko", "AMC_Neko.flf"));
        values.add(AMC_RAZOR = new BannerFont("AMC Razor", "AMC_Razor.flf"));
        values.add(AMC_RAZOR2 = new BannerFont("AMC Razor2", "AMC_Razor2.flf"));
        values.add(AMC_SLASH = new BannerFont("AMC Slash", "AMC_Slash.flf"));
        values.add(AMC_SLIDER = new BannerFont("AMC Slider", "AMC_Slider.flf"));
        values.add(AMC_THIN = new BannerFont("AMC Thin", "AMC_Thin.flf"));
        values.add(AMC_TUBES = new BannerFont("AMC Tubes", "AMC_Tubes.flf"));
        values.add(AMC_UNTITLED = new BannerFont("AMC Untitled", "AMC_Untitled.flf"));
        values.add(ANSI_SHADOW = new BannerFont("ANSI Shadow", "ANSI_Shadow.flf"));
        values.add(ASCII_NEW_ROMAN = new BannerFont("ASCII New Roman", "ASCII_New_Roman.flf"));
        values.add(ACROBATIC = new BannerFont("Acrobatic", "Acrobatic.flf"));
        values.add(ALLIGATOR = new BannerFont("Alligator", "Alligator.flf"));
        values.add(ALLIGATOR2 = new BannerFont("Alligator2", "Alligator2.flf"));
        values.add(ALPHA = new BannerFont("Alpha", "Alpha.flf"));
        values.add(ALPHABET = new BannerFont("Alphabet", "Alphabet.flf"));
        values.add(ARROWS = new BannerFont("Arrows", "Arrows.flf"));
        values.add(AVATAR = new BannerFont("Avatar", "Avatar.flf"));
        values.add(BANNER = new BannerFont("Banner", "Banner.flf"));
        values.add(BANNER3_D = new BannerFont("Banner3-D", "Banner3-D.flf"));
        values.add(BANNER3 = new BannerFont("Banner3", "Banner3.flf"));
        values.add(BANNER4 = new BannerFont("Banner4", "Banner4.flf"));
        values.add(BARBWIRE = new BannerFont("Barbwire", "Barbwire.flf"));
        values.add(BASIC = new BannerFont("Basic", "Basic.flf"));
        values.add(BEAR = new BannerFont("Bear", "Bear.flf"));
        values.add(BELL = new BannerFont("Bell", "Bell.flf"));
        values.add(BENJAMIN = new BannerFont("Benjamin", "Benjamin.flf"));
        values.add(BIG = new BannerFont("Big", "Big.flf"));
        values.add(BIG_CHIEF = new BannerFont("Big Chief", "Big_Chief.flf"));
        values.add(BIG_MONEY_NE = new BannerFont("Big Money-ne", "Big_Money-ne.flf"));
        values.add(BIG_MONEY_NW = new BannerFont("Big Money-nw", "Big_Money-nw.flf"));
        values.add(BIG_MONEY_SE = new BannerFont("Big Money-se", "Big_Money-se.flf"));
        values.add(BIG_MONEY_SW = new BannerFont("Big Money-sw", "Big_Money-sw.flf"));
        values.add(BIGFIG = new BannerFont("Bigfig", "Bigfig.flf"));
        values.add(BINARY = new BannerFont("Binary", "Binary.flf"));
        values.add(BLOCK = new BannerFont("Block", "Block.flf"));
        values.add(BLOCKS = new BannerFont("Blocks", "Blocks.flf"));
        values.add(BLOODY = new BannerFont("Bloody", "Bloody.flf"));
        values.add(BOLGER = new BannerFont("Bolger", "Bolger.flf"));
        values.add(BRACED = new BannerFont("Braced", "Braced.flf"));
        values.add(BRIGHT = new BannerFont("Bright", "Bright.flf"));
        values.add(BROADWAY = new BannerFont("Broadway", "Broadway.flf"));
        values.add(BROADWAY_KB = new BannerFont("Broadway KB", "Broadway_KB.flf"));
        values.add(BUBBLE = new BannerFont("Bubble", "Bubble.flf"));
        values.add(BULBHEAD = new BannerFont("Bulbhead", "Bulbhead.flf"));
        values.add(CALIGRAPHY = new BannerFont("Caligraphy", "Caligraphy.flf"));
        values.add(CALIGRAPHY2 = new BannerFont("Caligraphy2", "Caligraphy2.flf"));
        values.add(CALVIN_S = new BannerFont("Calvin S", "Calvin_S.flf"));
        values.add(CARDS = new BannerFont("Cards", "Cards.flf"));
        values.add(CATWALK = new BannerFont("Catwalk", "Catwalk.flf"));
        values.add(CHISELED = new BannerFont("Chiseled", "Chiseled.flf"));
        values.add(CHUNKY = new BannerFont("Chunky", "Chunky.flf"));
        values.add(COINSTAK = new BannerFont("Coinstak", "Coinstak.flf"));
        values.add(COLA = new BannerFont("Cola", "Cola.flf"));
        values.add(COLOSSAL = new BannerFont("Colossal", "Colossal.flf"));
        values.add(COMPUTER = new BannerFont("Computer", "Computer.flf"));
        values.add(CONTESSA = new BannerFont("Contessa", "Contessa.flf"));
        values.add(CONTRAST = new BannerFont("Contrast", "Contrast.flf"));
        values.add(COSMIKE = new BannerFont("Cosmike", "Cosmike.flf"));
        values.add(CRAWFORD = new BannerFont("Crawford", "Crawford.flf"));
        values.add(CRAWFORD2 = new BannerFont("Crawford2", "Crawford2.flf"));
        values.add(CRAZY = new BannerFont("Crazy", "Crazy.flf"));
        values.add(CRICKET = new BannerFont("Cricket", "Cricket.flf"));
        values.add(CURSIVE = new BannerFont("Cursive", "Cursive.flf"));
        values.add(CYBERLARGE = new BannerFont("Cyberlarge", "Cyberlarge.flf"));
        values.add(CYBERMEDIUM = new BannerFont("Cybermedium", "Cybermedium.flf"));
        values.add(CYBERSMALL = new BannerFont("Cybersmall", "Cybersmall.flf"));
        values.add(CYGNET = new BannerFont("Cygnet", "Cygnet.flf"));
        values.add(DANC4 = new BannerFont("DANC4", "DANC4.flf"));
        values.add(DWHISTLED = new BannerFont("DWhistled", "DWhistled.flf"));
        values.add(DANCING_FONT = new BannerFont("Dancing Font", "Dancing_Font.flf"));
        values.add(DECIMAL = new BannerFont("Decimal", "Decimal.flf"));
        values.add(DEF_LEPPARD = new BannerFont("Def Leppard", "Def_Leppard.flf"));
        values.add(DELTA_CORPS_PRIEST_1 = new BannerFont("Delta Corps Priest 1", "Delta_Corps_Priest_1.flf"));
        values.add(DIAMOND = new BannerFont("Diamond", "Diamond.flf"));
        values.add(DIET_COLA = new BannerFont("Diet Cola", "Diet_Cola.flf"));
        values.add(DIGITAL = new BannerFont("Digital", "Digital.flf"));
        values.add(DOH = new BannerFont("Doh", "Doh.flf"));
        values.add(DOOM = new BannerFont("Doom", "Doom.flf"));
        values.add(DOT_MATRIX = new BannerFont("Dot Matrix", "Dot_Matrix.flf"));
        values.add(DOUBLE = new BannerFont("Double", "Double.flf"));
        values.add(DOUBLE_SHORTS = new BannerFont("Double Shorts", "Double_Shorts.flf"));
        values.add(DR_PEPPER = new BannerFont("Dr Pepper", "Dr_Pepper.flf"));
        values.add(EFTI_CHESS = new BannerFont("Efti Chess", "Efti_Chess.flf"));
        values.add(EFTI_FONT = new BannerFont("Efti Font", "Efti_Font.flf"));
        values.add(EFTI_ITALIC = new BannerFont("Efti Italic", "Efti_Italic.flf"));
        values.add(EFTI_PITI = new BannerFont("Efti Piti", "Efti_Piti.flf"));
        values.add(EFTI_ROBOT = new BannerFont("Efti Robot", "Efti_Robot.flf"));
        values.add(EFTI_WALL = new BannerFont("Efti Wall", "Efti_Wall.flf"));
        values.add(EFTI_WATER = new BannerFont("Efti Water", "Efti_Water.flf"));
        values.add(ELECTRONIC = new BannerFont("Electronic", "Electronic.flf"));
        values.add(ELITE = new BannerFont("Elite", "Elite.flf"));
        values.add(EPIC = new BannerFont("Epic", "Epic.flf"));
        values.add(FENDER = new BannerFont("Fender", "Fender.flf"));
        values.add(FILTER = new BannerFont("Filter", "Filter.flf"));
        values.add(FIRE_FONT_K = new BannerFont("Fire Font-k", "Fire_Font-k.flf"));
        values.add(FIRE_FONT_S = new BannerFont("Fire Font-s", "Fire_Font-s.flf"));
        values.add(FLIPPED = new BannerFont("Flipped", "Flipped.flf"));
        values.add(FLOWER_POWER = new BannerFont("Flower Power", "Flower_Power.flf"));
        values.add(FOUR_TOPS = new BannerFont("Four Tops", "Four_Tops.flf"));
        values.add(FRAKTUR = new BannerFont("Fraktur", "Fraktur.flf"));
        values.add(FUN_FACE = new BannerFont("Fun Face", "Fun_Face.flf"));
        values.add(FUN_FACES = new BannerFont("Fun Faces", "Fun_Faces.flf"));
        values.add(FUZZY = new BannerFont("Fuzzy", "Fuzzy.flf"));
        values.add(GEORGI16 = new BannerFont("Georgi16", "Georgi16.flf"));
        values.add(GEORGIA11 = new BannerFont("Georgia11", "Georgia11.flf"));
        values.add(GHOST = new BannerFont("Ghost", "Ghost.flf"));
        values.add(GHOULISH = new BannerFont("Ghoulish", "Ghoulish.flf"));
        values.add(GLENYN = new BannerFont("Glenyn", "Glenyn.flf"));
        values.add(GOOFY = new BannerFont("Goofy", "Goofy.flf"));
        values.add(GOTHIC = new BannerFont("Gothic", "Gothic.flf"));
        values.add(GRACEFUL = new BannerFont("Graceful", "Graceful.flf"));
        values.add(GRADIENT = new BannerFont("Gradient", "Gradient.flf"));
        values.add(GRAFFITI = new BannerFont("Graffiti", "Graffiti.flf"));
        values.add(GREEK = new BannerFont("Greek", "Greek.flf"));
        values.add(HEART_LEFT = new BannerFont("Heart Left", "Heart_Left.flf"));
        values.add(HEART_RIGHT = new BannerFont("Heart Right", "Heart_Right.flf"));
        values.add(HENRY_3D = new BannerFont("Henry 3D", "Henry_3D.flf"));
        values.add(HEX = new BannerFont("Hex", "Hex.flf"));
        values.add(HIEROGLYPHS = new BannerFont("Hieroglyphs", "Hieroglyphs.flf"));
        values.add(HOLLYWOOD = new BannerFont("Hollywood", "Hollywood.flf"));
        values.add(HORIZONTAL_LEFT = new BannerFont("Horizontal Left", "Horizontal_Left.flf"));
        values.add(HORIZONTAL_RIGHT = new BannerFont("Horizontal Right", "Horizontal_Right.flf"));
        values.add(ICL_1900 = new BannerFont("ICL-1900", "ICL-1900.flf"));
        values.add(IMPOSSIBLE = new BannerFont("Impossible", "Impossible.flf"));
        values.add(INVITA = new BannerFont("Invita", "Invita.flf"));
        values.add(ISOMETRIC1 = new BannerFont("Isometric1", "Isometric1.flf"));
        values.add(ISOMETRIC2 = new BannerFont("Isometric2", "Isometric2.flf"));
        values.add(ISOMETRIC3 = new BannerFont("Isometric3", "Isometric3.flf"));
        values.add(ISOMETRIC4 = new BannerFont("Isometric4", "Isometric4.flf"));
        values.add(ITALIC = new BannerFont("Italic", "Italic.flf"));
        values.add(IVRIT = new BannerFont("Ivrit", "Ivrit.flf"));
        values.add(JS_BLOCK_LETTERS = new BannerFont("JS Block Letters", "JS_Block_Letters.flf"));
        values.add(JS_BRACKET_LETTERS = new BannerFont("JS Bracket Letters", "JS_Bracket_Letters.flf"));
        values.add(JS_CAPITAL_CURVES = new BannerFont("JS Capital Curves", "JS_Capital_Curves.flf"));
        values.add(JS_CURSIVE = new BannerFont("JS Cursive", "JS_Cursive.flf"));
        values.add(JS_STICK_LETTERS = new BannerFont("JS Stick Letters", "JS_Stick_Letters.flf"));
        values.add(JACKY = new BannerFont("Jacky", "Jacky.flf"));
        values.add(JAZMINE = new BannerFont("Jazmine", "Jazmine.flf"));
        values.add(JERUSALEM = new BannerFont("Jerusalem", "Jerusalem.flf"));
        values.add(KATAKANA = new BannerFont("Katakana", "Katakana.flf"));
        values.add(KBAN = new BannerFont("Kban", "Kban.flf"));
        values.add(KEYBOARD = new BannerFont("Keyboard", "Keyboard.flf"));
        values.add(KNOB = new BannerFont("Knob", "Knob.flf"));
        values.add(LCD = new BannerFont("LCD", "LCD.flf"));
        values.add(LARRY_3D = new BannerFont("Larry 3D", "Larry_3D.flf"));
        values.add(LEAN = new BannerFont("Lean", "Lean.flf"));
        values.add(LETTERS = new BannerFont("Letters", "Letters.flf"));
        values.add(LIL_DEVIL = new BannerFont("Lil Devil", "Lil_Devil.flf"));
        values.add(LINE_BLOCKS = new BannerFont("Line Blocks", "Line_Blocks.flf"));
        values.add(LINUX = new BannerFont("Linux", "Linux.flf"));
        values.add(LOCKERGNOME = new BannerFont("Lockergnome", "Lockergnome.flf"));
        values.add(MADRID = new BannerFont("Madrid", "Madrid.flf"));
        values.add(MARQUEE = new BannerFont("Marquee", "Marquee.flf"));
        values.add(MAXFOUR = new BannerFont("Maxfour", "Maxfour.flf"));
        values.add(MERLIN1 = new BannerFont("Merlin1", "Merlin1.flf"));
        values.add(MERLIN2 = new BannerFont("Merlin2", "Merlin2.flf"));
        values.add(MIKE = new BannerFont("Mike", "Mike.flf"));
        values.add(MINI = new BannerFont("Mini", "Mini.flf"));
        values.add(MIRROR = new BannerFont("Mirror", "Mirror.flf"));
        values.add(MNEMONIC = new BannerFont("Mnemonic", "Mnemonic.flf"));
        values.add(MODULAR = new BannerFont("Modular", "Modular.flf"));
        values.add(MORSE = new BannerFont("Morse", "Morse.flf"));
        values.add(MOSCOW = new BannerFont("Moscow", "Moscow.flf"));
        values.add(MSHEBREW210 = new BannerFont("Mshebrew210", "Mshebrew210.flf"));
        values.add(MUZZLE = new BannerFont("Muzzle", "Muzzle.flf"));
        values.add(NSCRIPT = new BannerFont("NScript", "NScript.flf"));
        values.add(NT_GREEK = new BannerFont("NT Greek", "NT_Greek.flf"));
        values.add(NV_SCRIPT = new BannerFont("NV Script", "NV_Script.flf"));
        values.add(NANCYJ_FANCY = new BannerFont("Nancyj-Fancy", "Nancyj-Fancy.flf"));
        values.add(NANCYJ_UNDERLINED = new BannerFont("Nancyj-Underlined", "Nancyj-Underlined.flf"));
        values.add(NANCYJ = new BannerFont("Nancyj", "Nancyj.flf"));
        values.add(NIPPLES = new BannerFont("Nipples", "Nipples.flf"));
        values.add(O8 = new BannerFont("O8", "O8.flf"));
        values.add(OS2 = new BannerFont("OS2", "OS2.flf"));
        values.add(OCTAL = new BannerFont("Octal", "Octal.flf"));
        values.add(OGRE = new BannerFont("Ogre", "Ogre.flf"));
        values.add(OLD_BANNER = new BannerFont("Old Banner", "Old_Banner.flf"));
        values.add(PATORJK_HEX = new BannerFont("Patorjk-HeX", "Patorjk-HeX.flf"));
        values.add(PATORJK_CHEESE = new BannerFont("Patorjk Cheese", "Patorjk_Cheese.flf"));
        values.add(PAWP = new BannerFont("Pawp", "Pawp.flf"));
        values.add(PEAKS = new BannerFont("Peaks", "Peaks.flf"));
        values.add(PEAKS_SLANT = new BannerFont("Peaks Slant", "Peaks_Slant.flf"));
        values.add(PEBBLES = new BannerFont("Pebbles", "Pebbles.flf"));
        values.add(PEPPER = new BannerFont("Pepper", "Pepper.flf"));
        values.add(POISON = new BannerFont("Poison", "Poison.flf"));
        values.add(PUFFY = new BannerFont("Puffy", "Puffy.flf"));
        values.add(PUZZLE = new BannerFont("Puzzle", "Puzzle.flf"));
        values.add(PYRAMID = new BannerFont("Pyramid", "Pyramid.flf"));
        values.add(RAMMSTEIN = new BannerFont("Rammstein", "Rammstein.flf"));
        values.add(RECTANGLES = new BannerFont("Rectangles", "Rectangles.flf"));
        values.add(RELIEF = new BannerFont("Relief", "Relief.flf"));
        values.add(RELIEF2 = new BannerFont("Relief2", "Relief2.flf"));
        values.add(REVERSE = new BannerFont("Reverse", "Reverse.flf"));
        values.add(ROMAN = new BannerFont("Roman", "Roman.flf"));
        values.add(ROTATED = new BannerFont("Rotated", "Rotated.flf"));
        values.add(ROUNDED = new BannerFont("Rounded", "Rounded.flf"));
        values.add(ROWAN_CAP = new BannerFont("Rowan Cap", "Rowan_Cap.flf"));
        values.add(ROZZO = new BannerFont("Rozzo", "Rozzo.flf"));
        values.add(RUNIC = new BannerFont("Runic", "Runic.flf"));
        values.add(RUNYC = new BannerFont("Runyc", "Runyc.flf"));
        values.add(SL_SCRIPT = new BannerFont("SL Script", "SL_Script.flf"));
        values.add(S_BLOOD = new BannerFont("S Blood", "S_Blood.flf"));
        values.add(SANTA_CLARA = new BannerFont("Santa Clara", "Santa_Clara.flf"));
        values.add(SCRIPT = new BannerFont("Script", "Script.flf"));
        values.add(SERIFCAP = new BannerFont("Serifcap", "Serifcap.flf"));
        values.add(SHADOW = new BannerFont("Shadow", "Shadow.flf"));
        values.add(SHIMROD = new BannerFont("Shimrod", "Shimrod.flf"));
        values.add(SHORT = new BannerFont("Short", "Short.flf"));
        values.add(SLANT = new BannerFont("Slant", "Slant.flf"));
        values.add(SLANT_RELIEF = new BannerFont("Slant Relief", "Slant_Relief.flf"));
        values.add(SLIDE = new BannerFont("Slide", "Slide.flf"));
        values.add(SMALL = new BannerFont("Small", "Small.flf"));
        values.add(SMALL_CAPS = new BannerFont("Small Caps", "Small_Caps.flf"));
        values.add(SMALL_ISOMETRIC1 = new BannerFont("Small Isometric1", "Small_Isometric1.flf"));
        values.add(SMALL_KEYBOARD = new BannerFont("Small Keyboard", "Small_Keyboard.flf"));
        values.add(SMALL_POISON = new BannerFont("Small Poison", "Small_Poison.flf"));
        values.add(SMALL_SCRIPT = new BannerFont("Small Script", "Small_Script.flf"));
        values.add(SMALL_SHADOW = new BannerFont("Small Shadow", "Small_Shadow.flf"));
        values.add(SMALL_SLANT = new BannerFont("Small Slant", "Small_Slant.flf"));
        values.add(SMALL_TENGWAR = new BannerFont("Small Tengwar", "Small_Tengwar.flf"));
        values.add(SOFT = new BannerFont("Soft", "Soft.flf"));
        values.add(SPEED = new BannerFont("Speed", "Speed.flf"));
        values.add(SPLIFF = new BannerFont("Spliff", "Spliff.flf"));
        values.add(STACEY = new BannerFont("Stacey", "Stacey.flf"));
        values.add(STAMPATE = new BannerFont("Stampate", "Stampate.flf"));
        values.add(STAMPATELLO = new BannerFont("Stampatello", "Stampatello.flf"));
        values.add(STANDARD = new BannerFont("Standard", "Standard.flf"));
        values.add(STAR_STRIPS = new BannerFont("Star Strips", "Star_Strips.flf"));
        values.add(STAR_WARS = new BannerFont("Star Wars", "Star_Wars.flf"));
        values.add(STELLAR = new BannerFont("Stellar", "Stellar.flf"));
        values.add(STFOREK = new BannerFont("Stforek", "Stforek.flf"));
        values.add(STICK_LETTERS = new BannerFont("Stick Letters", "Stick_Letters.flf"));
        values.add(STOP = new BannerFont("Stop", "Stop.flf"));
        values.add(STRAIGHT = new BannerFont("Straight", "Straight.flf"));
        values.add(STRONGER_THAN_ALL = new BannerFont("Stronger Than All", "Stronger_Than_All.flf"));
        values.add(SUB_ZERO = new BannerFont("Sub-Zero", "Sub-Zero.flf"));
        values.add(SWAMP_LAND = new BannerFont("Swamp Land", "Swamp_Land.flf"));
        values.add(SWAN = new BannerFont("Swan", "Swan.flf"));
        values.add(SWEET = new BannerFont("Sweet", "Sweet.flf"));
        values.add(THIS = new BannerFont("THIS", "THIS.flf"));
        values.add(TANJA = new BannerFont("Tanja", "Tanja.flf"));
        values.add(TENGWAR = new BannerFont("Tengwar", "Tengwar.flf"));
        values.add(TERM = new BannerFont("Term", "Term.flf"));
        values.add(TEST1 = new BannerFont("Test1", "Test1.flf"));
        values.add(THE_EDGE = new BannerFont("The Edge", "The_Edge.flf"));
        values.add(THICK = new BannerFont("Thick", "Thick.flf"));
        values.add(THIN = new BannerFont("Thin", "Thin.flf"));
        values.add(THORNED = new BannerFont("Thorned", "Thorned.flf"));
        values.add(THREE_POINT = new BannerFont("Three Point", "Three_Point.flf"));
        values.add(TICKS = new BannerFont("Ticks", "Ticks.flf"));
        values.add(TICKS_SLANT = new BannerFont("Ticks Slant", "Ticks_Slant.flf"));
        values.add(TILES = new BannerFont("Tiles", "Tiles.flf"));
        values.add(TINKER_TOY = new BannerFont("Tinker-Toy", "Tinker-Toy.flf"));
        values.add(TOMBSTONE = new BannerFont("Tombstone", "Tombstone.flf"));
        values.add(TRAIN = new BannerFont("Train", "Train.flf"));
        values.add(TREK = new BannerFont("Trek", "Trek.flf"));
        values.add(TSALAGI = new BannerFont("Tsalagi", "Tsalagi.flf"));
        values.add(TUBULAR = new BannerFont("Tubular", "Tubular.flf"));
        values.add(TWISTED = new BannerFont("Twisted", "Twisted.flf"));
        values.add(TWO_POINT = new BannerFont("Two Point", "Two_Point.flf"));
        values.add(USA_FLAG = new BannerFont("USA Flag", "USA_Flag.flf"));
        values.add(UNIVERS = new BannerFont("Univers", "Univers.flf"));
        values.add(VARSITY = new BannerFont("Varsity", "Varsity.flf"));
        values.add(WAVY = new BannerFont("Wavy", "Wavy.flf"));
        values.add(WEIRD = new BannerFont("Weird", "Weird.flf"));
        values.add(WET_LETTER = new BannerFont("Wet Letter", "Wet_Letter.flf"));
        values.add(WHIMSY = new BannerFont("Whimsy", "Whimsy.flf"));
        values.add(WOW = new BannerFont("Wow", "Wow.flf"));
        values.add(CIRCLE = new BannerFont("Circle", "circle.tlf"));
        values.add(EMBOSS = new BannerFont("Emboss", "emboss.tlf"));
        values.add(EMBOSS2 = new BannerFont("Emboss 2", "emboss2.tlf"));
        values.add(FUTURE = new BannerFont("Future", "future.tlf"));
        values.add(PAGGA = new BannerFont("Pagga", "pagga.tlf"));
        values.add(RUSTO = new BannerFont("Rusto", "rusto.tlf"));
        values.add(RUSTO_FAT = new BannerFont("Rusto Fat", "rustofat.tlf"));
        values.add(ASCII9 = new BannerFont("ASCII 9", "ascii9.tlf"));
        values.add(ASCII12 = new BannerFont("ASCII 12", "ascii12.tlf"));
        values.add(BIG_ASCII9 = new BannerFont("Big ASCII 9", "bigascii9.tlf"));
        values.add(BIG_ASCII12 = new BannerFont("Big ASCII 12", "bigascii12.tlf"));
        values.add(SMALL_ASCII9 = new BannerFont("Small ASCII 9", "smascii9.tlf"));
        values.add(SMALL_ASCII12 = new BannerFont("Small ASCII 12", "smascii12.tlf"));
        Map<String, BannerFont> map = new HashMap<>(values.size());
        for (BannerFont v : values) {
            map.put(v.name, v);
        }
        VALUES = Collections.unmodifiableList(values);
        MAP = Collections.unmodifiableMap(map);
    }

    protected final String name;
    protected final String filename;
    protected final Charset charset;

    protected BannerFont(String name) {
        this.name = name;
        this.filename = null;
        this.charset = StandardCharsets.UTF_8;
    }

    protected BannerFont(String name, String filename) {
        this.name = name;
        this.filename = filename;
        this.charset = StandardCharsets.UTF_8;
    }

    protected BannerFont(String name, String filename, Charset charset) {
        this.name = name;
        this.filename = filename;
        this.charset = charset;
    }

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }

    public Charset getCharset() {
        return charset;
    }

    public InputStream getInputStream() throws IOException {
        InputStream inputStream = BannerFont.class.getClassLoader().getResourceAsStream(FONT_DIR_PATH + filename);
        if (inputStream == null) {
            throw new RuntimeException("Failed to load font '" + this.name + "', the specified font does not exist.");
        }
        return convertIfZipped(inputStream);
    }

    /**
     * Returns a {@link ZipInputStream} if the input stream can be converted.
     * @param inputStream the input stream.
     * @return a {@link ZipInputStream} if the input stream can be converted.
     * @throws IOException if an exception occurs during converting.
     */
    protected static InputStream convertIfZipped(InputStream inputStream) throws IOException {
        // Detects zipped font.
        BufferedInputStream bufferedInputStream = inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream);
        if (isZipped(bufferedInputStream)) {
            // Expects a single anonymous entry.
            ZipInputStream zipInputStream = new ZipInputStream(bufferedInputStream);
            ZipEntry entry = zipInputStream.getNextEntry();
            if (entry == null) {
                throw new RuntimeException("Failed to convert the InputStream.");
            }
            return zipInputStream;
        } else {
            return bufferedInputStream;
        }
    }

    /**
     * Returns {@code true} if the buffered input stream start with {@code 0x504b0304}.
     * @param bufferedInputStream the buffered input stream.
     * @return {@code true} if the buffered input stream start with {@code 0x504b0304}.
     * @throws IOException if an exception occurs during detecting.
     */
    protected static boolean isZipped(BufferedInputStream bufferedInputStream) throws IOException {
        byte[] buf = new byte[4];
        bufferedInputStream.mark(4);
        bufferedInputStream.read(buf);
        bufferedInputStream.reset();
        return Arrays.equals(buf, new byte[]{0x50, 0x4b, 0x03, 0x04});
    }

    /**
     * Returns all fonts.
     * @return all fonts.
     */
    public static List<BannerFont> values() {
        return VALUES;
    }

    /**
     * Returns the font with the specified name, or {@code null} if the font does not exist.
     * @param name the font name.
     * @return the font with the specified name, or {@code null} if the font does not exist.
     */
    public static BannerFont get(String name) {
        return MAP.get(name);
    }

    /**
     * Returns the font with the specified name, or {@code defaultValue} if the font does not exist.
     * @param name         the font name.
     * @param defaultValue the default font.
     * @return the font with the specified name, or {@code defaultValue} if the font does not exist.
     */
    public static BannerFont getOrDefault(String name, BannerFont defaultValue) {
        BannerFont font;
        return (font = MAP.get(name)) != null ? font : defaultValue;
    }

}
