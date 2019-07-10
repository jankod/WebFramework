var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
define(["require", "exports", "./ClientAction"], function (require, exports, ClientAction_1) {
    "use strict";
    var JsEventAction = (function (_super) {
        __extends(JsEventAction, _super);
        function JsEventAction(eleId, serverListenerId, actionName, pageId) {
            var _this = _super.call(this) || this;
            if (_this.eleId === undefined)
                _this.eleId = null;
            if (_this.serverListenerId === undefined)
                _this.serverListenerId = null;
            if (_this.actionName === undefined)
                _this.actionName = null;
            if (_this.pageId === undefined)
                _this.pageId = null;
            _this.eleId = eleId;
            _this.serverListenerId = serverListenerId;
            _this.actionName = actionName;
            _this.pageId = pageId;
            return _this;
        }
        /**
         *
         * @return {string}
         */
        JsEventAction.prototype.getPageId = function () {
            return this.pageId;
        };
        return JsEventAction;
    }(ClientAction_1.ClientAction));
    exports.JsEventAction = JsEventAction;
    JsEventAction["__class"] = "hr.JsEventAction";
});
