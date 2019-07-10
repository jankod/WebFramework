var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
define(["require", "exports", "./ClientAction"], function (require, exports, ClientAction_1) {
    "use strict";
    var ClientCode = (function () {
        function ClientCode() {
        }
        ClientCode.main = function (args) {
            var w = new WebSocket("ws://" + window.location.hostname + ":" + window.location.port + "/state?pageId=" + window["PAGE_ID"]);
            var actions = ([]);
            /* add */ (actions.push(new ClientCode.ClientCode$0()) > 0);
            console.log(actions);
            w.onopen = function (t) {
                console.log("Open connection");
                return null;
            };
            console.log("pocinje klijent code :))) ");
            alert("dela#");
        };
        return ClientCode;
    }());
    exports.ClientCode = ClientCode;
    ClientCode["__class"] = "hr.ClientCode";
    (function (ClientCode) {
        var ClientCode$0 = (function (_super) {
            __extends(ClientCode$0, _super);
            function ClientCode$0() {
                return _super.call(this) || this;
            }
            /**
             *
             * @return {string}
             */
            ClientCode$0.prototype.getPageId = function () {
                return "sadas";
            };
            return ClientCode$0;
        }(ClientAction_1.ClientAction));
        ClientCode.ClientCode$0 = ClientCode$0;
    })(ClientCode = exports.ClientCode || (exports.ClientCode = {}));
    exports.ClientCode = ClientCode;
    ClientCode.main(null);
});
