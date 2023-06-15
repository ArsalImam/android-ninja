module.exports = `
package {{packageName}};

public class PKeys {
    {{#keys}} 
    public static String {{.}};
    {{/keys}}
}
`