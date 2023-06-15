let cypher = (value) => {
    value = Buffer.from(value).toString('base64');
    return _corruptStr(value);
}

let toInt = (val) => {
    let ints = [[]], c = 0;
    for (var i = 0; i < val.length; i++) {
        if (i > 19 && i % 20 == 0) {
            c++;
            if (!ints[c]) ints[c] = [];
        }
        ints[c].push(val.charCodeAt(i));
    }
    let str = "";
    ints.forEach((x, i) => { 
        str += x.join(",") + "\n\t\t"; 
        if ((i + 1) != ints.length) str += ",";
    });
    return str;
}

let getBase64 = () => {
    let ints =[ 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47]
    let str = "";
    ints.forEach((x, i) => { 
        str += `${x}`;
        if ((i + 1) != ints.length) str += ",";
        if (i > 19 && i % 20 == 0) str += "\n\t\t";
    });
    return str;
}

let _corruptStr = (encVal) => {
    let l = encVal.length;
    if (l >= 10) l = 9;
    let num = Math.round(Math.random() * (l));
    encVal = encVal.slice(0, num) + findChar(1) + encVal.slice(num);
    return _appendIndexToInsert(encVal, num);
}

let _appendIndexToInsert = (val, num) => {
    return num + val;
}

let findChar = (l) => {
    let randomChars = 'abcdefghijklmnopqrstuvwxyz0123456789';
    var result = '';
    for (var i = 0; i < l; i++) {
        result += randomChars.charAt(Math.floor(Math.random() * randomChars.length));
    }
    return result;
}

module.exports = { cypher, toInt, getBase64, findChar };