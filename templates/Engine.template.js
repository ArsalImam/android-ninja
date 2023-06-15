module.exports = `
package {{packageName}};

import {{packageName}}.view.PKeys;
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
        {{bytes}}
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

`;