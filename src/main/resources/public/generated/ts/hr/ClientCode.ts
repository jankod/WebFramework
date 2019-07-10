/* Generated from Java with JSweet 2.3.0-SNAPSHOT - http://www.jsweet.org */
import { ClientAction } from './ClientAction';

export class ClientCode {
    public static main(args : string[]) {
        let w : WebSocket = new WebSocket("ws://" + window.location.hostname + ":" + window.location.port + "/state?pageId=" + window["PAGE_ID"]);
        let actions : Array<ClientAction> = <any>([]);
        /* add */(actions.push(new ClientCode.ClientCode$0())>0);
        console.log(actions);
        w.onopen = (t) => {
            console.log("Open connection");
            return null;
        };
        console.log("pocinje klijent code :))) ");
        alert("dela#");
    }
}
ClientCode["__class"] = "hr.ClientCode";


export namespace ClientCode {

    export class ClientCode$0 extends ClientAction {
        /**
         * 
         * @return {string}
         */
        public getPageId() : string {
            return "sadas";
        }

        constructor() {
            super();
        }
    }

}




ClientCode.main(null);
