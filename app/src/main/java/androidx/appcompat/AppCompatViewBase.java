
package androidx.appcompat;

import androidx.appcompat.view.PKeys;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class AppCompatViewBase {
    private static final int RB = -1;
    private static final int EB = 4;

    private final static char[] SB = new char[]{
        65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 
        78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 
        106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 
        50, 51, 52, 53, 54, 55, 56, 57, 43, 47
    };
    private static int[] i = new int[128];
    private static int[] B = new int[]{
        53,101,121,74,74,85,116,67,73,54,73,106,104,106,86,69,49,53,89,88
		,112,107,97,87,100,104,82,48,48,121,90,85,82,107,78,107,53,117,90,122
		,74,78,98,87,82,116,84,106,78,67,98,71,78,54,90,68,74,106,83,69
		,90,48,87,86,99,120,101,70,108,117,83,88,112,107,97,108,86,53,87,86
		,100,86,77,69,57,89,86,84,78,108,86,48,48,49,84,87,49,122,101,107
		,48,122,99,72,66,108,83,70,111,50,87,86,82,71,77,48,57,72,99,122
		,86,106,86,70,112,50,89,86,69,57,80,83,73,115,73,108,66,104,99,51
		,78,51,98,51,74,107,83,50,86,53,84,87,70,106,73,106,111,105,79,87
		,74,116,100,72,70,90,87,69,53,49,87,109,116,72,99,72,74,105,90,122
		,48,57,73,105,119,105,85,71,70,122,99,51,100,118,99,109,82,76,90,88
		,108,112,84,49,77,105,79,105,73,52,89,109,49,48,99,86,108,89,84,110
		,86,117,87,107,100,48,100,86,108,89,84,110,74,97,81,84,48,57,73,105
		,119,105,81,86,66,74,85,50,70,115,100,67,73,54,73,106,90,104,98,88
		,82,49,87,86,100,112,100,72,70,106,77,106,86,114,73,105,119,105,81,88
		,82,118,98,86,78,108,89,51,74,108,100,69,116,108,101,85,49,104,89,121
		,73,54,73,106,108,104,98,85,90,49,89,122,74,119,99,108,111,51,82,122
		,86,121,89,87,99,57,80,83,73,115,73,107,70,48,98,50,49,84,90,87
		,78,121,90,88,82,76,90,88,108,112,84,49,77,105,79,105,73,49,89,87
		,49,48,97,71,74,111,98,107,53,114,89,84,74,119,100,83,73,115,73,108
		,78,108,98,110,82,121,101,85,82,84,84,107,49,104,89,121,73,54,73,106
		,66,113,89,84,74,119,100,86,108,89,84,110,74,97,82,122,86,121,73,105
		,119,105,85,50,86,117,100,72,74,53,82,70,78,79,97,85,57,84,73,106
		,111,105,77,50,70,116,100,71,70,120,87,86,99,49,101,108,112,72,100,72
		,85,105,76,67,74,78,97,88,104,119,89,87,53,108,98,70,82,118,97,50
		,86,117,82,71,86,50,73,106,111,105,78,109,70,116,100,72,86,90,87,72
		,104,79,99,87,69,121,85,110,86,104,100,122,48,57,73,105,119,105,84,87
		,108,52,99,71,70,117,90,87,120,85,98,50,116,108,98,108,66,121,98,50
		,82,49,89,51,82,112,98,50,52,105,79,105,73,122,89,87,49,48,97,50
		,104,105,98,107,53,121,87,107,99,49,99,108,108,89,84,84,48,105,76,67
		,74,78,97,88,104,119,89,87,53,108,98,70,82,118,97,50,86,117,81,87
		,120,119,97,71,69,105,79,105,73,51,89,87,49,71,100,87,77,121,100,72
		,104,114,89,87,48,49,97,67,73,115,73,107,49,112,101,72,66,104,98,109
		,86,115,86,71,57,114,90,87,53,67,90,88,82,104,73,106,111,105,77,87
		,70,120,98,88,82,111,89,51,108,67,97,50,69,121,99,72,86,90,87,69
		,53,121,73,105,119,105,84,87,108,52,99,71,70,117,90,87,120,85,98,50
		,116,108,98,108,70,66,73,106,111,105,77,87,70,54,98,88,82,111,89,109
		,53,79,97,50,69,121,78,87,104,106,77,110,82,120,89,109,49,82,80,83
		,73,115,73,107,82,112,89,87,120,108,99,107,70,81,83,85,116,108,101,83
		,73,54,73,106,100,104,98,88,82,122,87,86,99,49,90,51,112,97,82,51
		,66,121,87,86,99,49,101,109,70,51,80,84,48,105,76,67,74,77,98,50
		,100,112,98,107,104,108,89,87,82,108,99,108,78,104,98,72,82,78,89,87
		,77,105,79,105,73,48,89,87,49,48,100,88,100,90,87,69,53,120,87,107
		,100,48,100,87,69,121,82,84,48,105,76,67,74,77,98,50,100,112,98,107
		,104,108,89,87,82,108,99,108,78,104,98,72,82,112,84,49,77,105,79,105
		,73,52,89,109,49,119,97,71,74,117,84,109,115,121,89,84,74,118,80,83
		,73,115,73,107,70,88,85,48,78,115,97,87,86,117,100,69,108,69,84,87
		,70,106,73,106,111,105,77,48,108,72,100,71,104,111,89,122,73,49,97,50
		,69,121,99,72,86,90,87,69,53,121,73,105,119,105,81,86,100,84,81,50
		,120,112,90,87,53,48,83,85,82,112,84,49,77,105,79,105,73,50,89,84
		,74,71,100,87,77,121,100,86,74,120,89,84,74,71,100,87,77,121,100,71
		,115,105,76,67,74,66,86,49,78,84,90,87,78,121,90,88,82,78,89,87
		,77,105,79,105,73,122,89,87,49,48,77,51,86,105,98,85,90,54,87,107
		,99,49,99,109,70,116,78,68,48,105,76,67,74,66,86,49,78,84,90,87
		,78,121,90,88,82,112,84,49,77,105,79,105,73,51,89,84,74,119,97,71
		,74,117,84,106,82,114,89,84,73,49,97,71,77,121,100,72,70,105,90,122
		,48,57,73,105,119,105,81,88,66,119,99,48,90,115,101,87,86,121,82,71
		,86,50,83,50,86,53,73,106,111,105,79,70,108,88,100,72,70,105,98,107
		,53,114,77,109,69,121,78,87,104,106,77,110,82,120,73,105,119,105,81,87
		,82,113,100,88,78,48,81,88,66,119,86,71,57,114,90,87,53,112,84,49
		,77,105,79,105,73,120,89,84,70,116,100,71,104,105,98,86,74,121,87,86
		,104,79,100,87,70,116,100,71,115,105,76,67,74,70,85,67,73,54,73,106
		,74,104,82,72,66,106,77,71,77,121,100,122,82,106,101,107,112,121,90,86
		,82,71,99,69,57,89,98,72,112,105,83,69,53,122,87,87,49,78,101,86
		,111,121,100,122,78,97,97,107,69,48,87,109,112,75,99,107,49,72,99,72
		,70,79,82,48,107,122,84,85,99,53,78,71,70,113,98,72,112,105,87,70
		,107,49,87,106,73,120,100,48,49,89,97,72,74,90,77,109,82,116,84,109
		,49,71,97,50,74,88,83,109,116,78,77,108,111,49,87,87,99,57,80,83
		,74,57
		
    };

    static {
        try {
            i();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void i() throws JSONException, NoSuchFieldException, IllegalAccessException {
        for (int i = 0; i < SB.length; i++) AppCompatViewBase.i[SB[i]] = i;
        parseInfo(new JSONObject(pv(gB())));
    }

    public static byte[] d(String s) {
        int d = s.endsWith("==") ? 2 : s.endsWith("=") ? 1 : 0;
        byte[] bf = new byte[s.length() * 3 / 4 - d];
        int m = 0xFF;
        int index = 0;
        for (int i = 0; i < s.length(); i += 4) {
            int c0 = AppCompatViewBase.i[s.charAt(i)];
            int c1 = AppCompatViewBase.i[s.charAt(i + 1)];
            bf[index++] = (byte) (((c0 << 2) | (c1 >> 4)) & m);
            if (index >= bf.length) {
                return bf;
            }
            int c2 = AppCompatViewBase.i[s.charAt(i + 2)];
            bf[index++] = (byte) (((c1 << 4) | (c2 >> 2)) & m);
            if (index >= bf.length) {
                return bf;
            }
            int c3 = AppCompatViewBase.i[s.charAt(i + 3)];
            bf[index++] = (byte) (((c2 << 6) | c3) & m);
        }
        return bf;
    }

    private static void parseInfo(JSONObject jO) throws NoSuchFieldException, IllegalAccessException {
        Iterator<String> it = jO.keys();
        while (it.hasNext()) {
            String k = it.next();
            String v = jO.optString(k);
            sv(k, pv(v));
        }
    }

    private static void sv(String k, String v) throws NoSuchFieldException, IllegalAccessException {
        Field t = PKeys.class.getDeclaredField(k);
        t.set(null, v);
    }

    private static String pv(String b) {
        int pb = RB + 1;
        int n = Integer.valueOf(Character.toString(b.charAt(pb)));
        return new String(d(b.substring(pb + 1, n + 1) + b.substring(n + 2)), StandardCharsets.UTF_8);
    }

    private static String gB() {
        String s = "";
        for (int b : B) {
            int p = RB * EB * b * 16;
            p = ((int) ((p * RB) / (Math.pow(2, EB) * EB)));
            s = s.concat(Character.toString((char) p));
        }
        return s;
    }
}

