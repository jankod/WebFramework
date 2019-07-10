var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
define(["require", "exports", "./Action"], function (require, exports, Action_1) {
    "use strict";
    var ClientAction = (function (_super) {
        __extends(ClientAction, _super);
        function ClientAction() {
            return _super !== null && _super.apply(this, arguments) || this;
        }
        return ClientAction;
    }(Action_1.Action));
    exports.ClientAction = ClientAction;
    ClientAction["__class"] = "hr.ClientAction";
});
