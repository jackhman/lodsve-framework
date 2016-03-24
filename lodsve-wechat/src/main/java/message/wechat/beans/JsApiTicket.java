package message.wechat.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通过JS-API调用的票据.
 *
 * @author sunhao(sunhao.java@gmail.com)
 * @version V1.0, 16/2/23 上午11:55
 */
@ApiModel(description = "通过JS-API调用的票据")
public class JsApiTicket {
    @ApiModelProperty(value = "异常代码", required = true)
    private int errcode;
    @ApiModelProperty(value = "异常信息", required = true)
    private String errmsg;
    @ApiModelProperty(value = "票据", required = true)
    private String ticket;
    @ApiModelProperty(value = "有效时长", required = true)
    private long expires_in;

    @ApiModelProperty(value = "异常代码", required = true)
    public int getErrcode() {
        return errcode;
    }

    @ApiModelProperty(value = "异常代码", required = true)
    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    @ApiModelProperty(value = "异常信息", required = true)
    public String getErrmsg() {
        return errmsg;
    }

    @ApiModelProperty(value = "异常信息", required = true)
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @ApiModelProperty(value = "票据", required = true)
    public String getTicket() {
        return ticket;
    }

    @ApiModelProperty(value = "票据", required = true)
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @ApiModelProperty(value = "有效时长", required = true)
    public long getExpires_in() {
        return expires_in;
    }

    @ApiModelProperty(value = "有效时长", required = true)
    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }
}
