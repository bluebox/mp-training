import {
  Document,
  Page,
  Text,
  View,
  StyleSheet,
  PDFViewer,
} from "@react-pdf/renderer";
// Create styles
const styles = StyleSheet.create({
  page: {
    color: "black",
  },
  section: {
    margin: 10,
    padding: 10,
  },
  viewer: {
    width: window.innerWidth,
    height: window.innerHeight,
  },
  header:{
    flexDirection: "row",
    justifyContent: "center",
    margin: 20,
    padding: 10,
    backgroundColor: "gray"
  }
});

function BasicPdf(props) {
    const {user} =props;
  return (
    <PDFViewer style={styles.viewer}>
      {/* Start of the document*/}
      <Document>
        {/*render a single page*/}
        <Page size="A4" style={styles.page}>
          <View style={styles.header}>
            <Text>User Data</Text>
          </View>
          <View style={styles.section}>
            <Text>Name:{user.name}</Text>
            <Text>CustomerId:{user.customerId}</Text>
          </View>
        </Page>
      </Document>
    </PDFViewer>
  );
}

export default BasicPdf;