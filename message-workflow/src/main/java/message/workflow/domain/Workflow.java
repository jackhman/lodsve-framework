package message.workflow.domain;

import message.base.utils.ListUtils;
import org.apache.commons.collections.CollectionUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * 工作流.
 *
 * @author sunhao(sunhao.java@gmail.com)
 * @version V1.0, 2015-11-18 下午1:43
 */
@Entity
@Table(name = "t_workflow")
public class Workflow {
    @Id
    @GeneratedValue(generator = "snowflake")
    private Long id;
    @Column
    private String name;
    @Column
    private String code;
    @Column
    private String domain;
    @Column
    private int version = 0;
    @Column
    private String handler;
    @Column(length = 3000)
    private String xml;
    @Column(name = "xml_md")
    private String xmlMd5;

    private Class<?> handlerClass;
    private List<FlowNode> nodes;
    private Class<?> domainClass;
    private FlowNode startNode;
    private FlowNode endNode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getXmlMd5() {
        return xmlMd5;
    }

    public void setXmlMd5(String xmlMd5) {
        this.xmlMd5 = xmlMd5;
    }

    public Class<?> getHandlerClass() {
        return handlerClass;
    }

    public void setHandlerClass(Class<?> handlerClass) {
        this.handlerClass = handlerClass;
    }

    public List<FlowNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<FlowNode> nodes) {
        this.nodes = nodes;

        // 结束节点
        FlowNode endNode = ListUtils.findOne(nodes, new ListUtils.Decide<FlowNode>() {
            @Override
            public boolean judge(FlowNode target) {
                return "end".equals(target.getTo());
            }
        });
        setEndNode(endNode);

        // 开始节点
        FlowNode startNode = findStartNode(endNode, nodes);
        setStartNode(startNode);
    }

    private FlowNode findStartNode(FlowNode node, List<FlowNode> nodes) {
        if (CollectionUtils.isEmpty(nodes) || node == null) {
            return null;
        }

        final String code = node.getCode();
        FlowNode _node = ListUtils.findOne(nodes, new ListUtils.Decide<FlowNode>() {
            @Override
            public boolean judge(FlowNode target) {
                return code.equals(target.getTo());
            }
        });

        if (_node == null) {
            return node;
        } else {
            return findStartNode(startNode, nodes);
        }
    }

    public Class<?> getDomainClass() {
        return domainClass;
    }

    public void setDomainClass(Class<?> domainClass) {
        this.domainClass = domainClass;
    }

    public FlowNode getStartNode() {
        return startNode;
    }

    public void setStartNode(FlowNode startNode) {
        this.startNode = startNode;
    }

    public FlowNode getEndNode() {
        return endNode;
    }

    public void setEndNode(FlowNode endNode) {
        this.endNode = endNode;
    }
}