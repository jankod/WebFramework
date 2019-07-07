"use strict";
class Action {
}
exports.Action = Action;
Action["__class"] = "hr.Action";
(function (Action) {
    class User {
        constructor() {
            this.id = 22;
        }
    }
    Action.User = User;
    User["__class"] = "hr.Action.User";
})(Action = exports.Action || (exports.Action = {}));
