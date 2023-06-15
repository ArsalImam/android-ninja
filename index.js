(async () => {
    const commandLineArgs = require('command-line-args')
    const options = commandLineArgs([
        { name: 'module', alias: 'm', type: String,  defaultOption: true, defaultValue: 'app' }
    ])
    const fs = require('fs');
    const Mustache = require("mustache");
    const { cypher, toInt, getBase64, findChar } = require("./crypt.js");
    const { SymfonyStyle } = require('symfony-style-console')
    const io = new SymfonyStyle()

    let secrets = {};
    let keysJson = ["IP"];
    keysJson = keysJson.concat(JSON.parse(fs.readFileSync(`./keys.json`)));
    keysJson.push("EP");

    io.title('Pure VPN - Android Ninja Utility');
    io.success('Please share the required information to configure the project');

    let requestInformation = async (io, secrets, keysJson) => {
        for (let key of keysJson) {
            if (key == "EP" || key == "IP") {
                secrets[key] = cypher(findChar(64));
            } else {
                secrets[key] = cypher(await io.ask(`Please share password for ${key}`));
            }
        }
        return secrets;
    }
    secrets = await requestInformation(io, secrets, keysJson);

    let packageName = "androidx.appcompat";
    let keyPackageName = `${packageName}.view`;
    let javaSrc = `./${options.module}/src/main/java/`;

    let engineTemplate = require('./templates/Engine.template');
    let ninjaEngineTemplate = require('./templates/NinjaEngine.template');
    let ninjaKeysTemplate = require('./templates/NinjaKeys.template');

    let b64 = getBase64();
    let engineRendered = Mustache.render(engineTemplate, { packageName, bytes: toInt(cypher(JSON.stringify(secrets))), base64: b64 });
    let ninjaRendered = Mustache.render(ninjaEngineTemplate, { packageName });
    let keysRendered = Mustache.render(ninjaKeysTemplate, { packageName: `keyPackageName`, keys: Object.keys(secrets) });
    let rg = /\./g;
    let pkgPath = `${javaSrc}${packageName.replace(rg, "/")}`,
        keyPkgPath = `${javaSrc}${keyPackageName.replace(rg, "/")}`;
    fs.rmdirSync(`${pkgPath}`, { recursive: true, force: true });
    fs.mkdirSync(`${keyPkgPath}`, { recursive: true });

    fs.writeFileSync(`${keyPkgPath}/PKeys.java`, keysRendered);
    fs.writeFileSync(`${pkgPath}/AppCompatViewHelper.java`, ninjaRendered);
    fs.writeFileSync(`${pkgPath}/AppCompatViewBase.java`, engineRendered);
})()