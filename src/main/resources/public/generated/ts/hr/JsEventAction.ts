/* Generated from Java with JSweet 2.3.0-SNAPSHOT - http://www.jsweet.org */
import { ClientAction } from './ClientAction';

export class JsEventAction extends ClientAction {
    /*private*/ eleId : string;

    /*private*/ serverListenerId : string;

    /*private*/ actionName : string;

    /*private*/ pageId : string;

    public constructor(eleId : string, serverListenerId : string, actionName : string, pageId : string) {
        super();
        if(this.eleId===undefined) this.eleId = null;
        if(this.serverListenerId===undefined) this.serverListenerId = null;
        if(this.actionName===undefined) this.actionName = null;
        if(this.pageId===undefined) this.pageId = null;
        this.eleId = eleId;
        this.serverListenerId = serverListenerId;
        this.actionName = actionName;
        this.pageId = pageId;
    }

    /**
     * 
     * @return {string}
     */
    public getPageId() : string {
        return this.pageId;
    }
}
JsEventAction["__class"] = "hr.JsEventAction";



